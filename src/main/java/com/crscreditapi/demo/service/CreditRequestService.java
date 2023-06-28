package com.crscreditapi.demo.service;

import com.crscreditapi.demo.connector.CRSConnector;
import com.crscreditapi.demo.dto.crs.AuthRequestDto;
import com.crscreditapi.demo.dto.crs.AuthResponseDto;
import com.crscreditapi.demo.dto.crs.EquifaxRequestDto;
import com.crscreditapi.demo.enumeration.CreditRequestSource;
import com.crscreditapi.demo.enumeration.CreditRequestStatus;
import com.crscreditapi.demo.model.mysql.CreditRequest;
import com.crscreditapi.demo.repository.CreditRequestRepository;
import com.crscreditapi.demo.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class CreditRequestService {

    private static final Logger logger = LoggerFactory.getLogger(CreditRequestService.class);

    private final CreditRequestRepository creditRequestRepository;

    private final CRSConnector crsConnector;

    public CreditRequestService(CreditRequestRepository creditRequestRepository,
                                CRSConnector crsConnector) {
        this.creditRequestRepository = creditRequestRepository;
        this.crsConnector = crsConnector;
    }

    public AuthResponseDto authenticate(AuthRequestDto authRequest) {
        return crsConnector.authenticate(authRequest);
    }

    public CreditRequest findCreditRequestByPdfReportId(String pdfReportId) {
        return creditRequestRepository.findByPdfReportId(pdfReportId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CreditRequest not found"));
    }

    public CreditRequest findCreditRequestById(Long id) {
        return creditRequestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CreditRequest not found"));
    }

    public CreditRequest submitCreditRequest(EquifaxRequestDto equifaxRequest) {
        LocalDateTime thisTimeYesterday = LocalDateTime.now().minusDays(1L);
        Pageable pageable = PageUtil.of(0, 1, "startDate", "desc");

//        Page<CreditRequest> creditRequestPage = creditRequestRepository.findAllBySsnAndStartDateGreaterThanAndStatus(
//                        equifaxRequest.getSsn(),
//                        thisTimeYesterday,
//                        CreditRequestStatus.COMPLETED,
//                        pageable);
//
//        if (creditRequestPage.getTotalElements() > 0) {
//            return getCachedEquifaxResponse(equifaxRequest, creditRequestPage.getContent().get(0));
//        }

        return submitNewCreditRequest(equifaxRequest);
    }

    private CreditRequest getCachedEquifaxResponse(EquifaxRequestDto equifaxRequest, CreditRequest previousCreditRequest) {
        CreditRequest creditRequest = new CreditRequest();
        creditRequest.setEndDate(previousCreditRequest.getEndDate());
        creditRequest.setPdfReportId(previousCreditRequest.getPdfReportId());
        creditRequest.setRequestDate(LocalDateTime.now());
        creditRequest.setSource(CreditRequestSource.CACHE);
        creditRequest.setSsn(equifaxRequest.getSsn());
        creditRequest.setStartDate(previousCreditRequest.getStartDate());
        creditRequest.setStatus(CreditRequestStatus.COMPLETED);

        return creditRequestRepository.save(creditRequest);
    }

    private CreditRequest submitNewCreditRequest(EquifaxRequestDto equifaxRequest) {
        CreditRequest creditRequest = new CreditRequest();
        creditRequest.setRequestDate(LocalDateTime.now());
        creditRequest.setSource(CreditRequestSource.API);
        creditRequest.setSsn(equifaxRequest.getSsn());
        creditRequest.setStartDate(LocalDateTime.now());
        creditRequest.setStatus(CreditRequestStatus.REQUESTED);
        creditRequest = creditRequestRepository.save(creditRequest);

        equifaxRequest.setCreditRequestId(creditRequest.getId());

        logger.info("About to call async method");
        // Async
        crsConnector.postEquifaxCreditReportRequest(equifaxRequest)
                .thenAccept(equifaxResponse -> {
                    logger.info("Returned from async method");
                    CreditRequest savedCreditRequest = creditRequestRepository.findById(equifaxResponse.getCreditRequestId()).orElseThrow(() -> new RuntimeException("CreditRequest not found. This should never happen"));
                    savedCreditRequest.setEndDate(LocalDateTime.now());
                    savedCreditRequest.setPdfReportId(equifaxResponse.getPdfReportId());
                    savedCreditRequest.setStatus(CreditRequestStatus.COMPLETED);
                    creditRequestRepository.save(savedCreditRequest);
                });

        logger.info("Returning after handing off to async method");

        return creditRequest;
    }
}
