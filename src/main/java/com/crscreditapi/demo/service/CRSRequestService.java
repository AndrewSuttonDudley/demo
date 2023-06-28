package com.crscreditapi.demo.service;

import com.crscreditapi.demo.enumeration.CreditRequestSource;
import com.crscreditapi.demo.enumeration.Vendor;
import com.crscreditapi.demo.model.mongo.CRSRequest;
import com.crscreditapi.demo.repository.mongo.CRSRequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CRSRequestService {

    private final CRSRequestRepository crsRequestRepository;

    public CRSRequestService(CRSRequestRepository crsRequestRepository) {
        this.crsRequestRepository = crsRequestRepository;
    }

    public List<CRSRequest> findAllByCriteria(Long creditRequestId, CreditRequestSource source, LocalDateTime startDate, LocalDateTime endDate, Vendor vendor) {
        return crsRequestRepository.findAllByCriteria(creditRequestId, startDate, endDate, vendor);
    }

}
