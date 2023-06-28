package com.crscreditapi.demo.service;

import com.crscreditapi.demo.dto.CreditRequestDto;
import com.crscreditapi.demo.dto.crs.EquifaxRequestDto;
import com.crscreditapi.demo.enumeration.CreditRequestSource;
import com.crscreditapi.demo.enumeration.Vendor;
import com.crscreditapi.demo.model.mongo.APIRequest;
import com.crscreditapi.demo.model.mongo.sub.RequestResponsePayload;
import com.crscreditapi.demo.repository.mongo.APIRequestRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class APIRequestService {

    private static final Logger logger = LoggerFactory.getLogger(APIRequestService.class);

    public static final InheritableThreadLocal<APIRequest> apiRequestData = new InheritableThreadLocal<>();

    private final APIRequestRepository apiRequestRepository;

    public APIRequestService(APIRequestRepository apiRequestRepository) {
        this.apiRequestRepository = apiRequestRepository;
    }

    public List<APIRequest> findAll() {
        List<APIRequest> apiRequests = apiRequestRepository.findAll();
        logger.info("apiRequestRepository.findAll() returned {} APIRequests", apiRequests.size());
        return apiRequests;
    }

    public List<APIRequest> findAllByCriteria(Long creditRequestId, CreditRequestSource source, LocalDateTime startDate, LocalDateTime endDate, Vendor vendor) {
        return apiRequestRepository.findAllByCriteria(creditRequestId, source, startDate, endDate, vendor);
    }

    public APIRequest getThreadLocalAPIRequest() {
        return apiRequestData.get();
    }

    private void handleRequestBodyEquifaxRequestDto(EquifaxRequestDto equifaxRequestDto) {
        APIRequest apiRequest = apiRequestData.get();
        if (apiRequest == null) {
            logger.warn("Creating new thread local APIRequest for EquifaxRequestDto. This is not what should happen.");
            apiRequest = new APIRequest();
            apiRequestData.set(apiRequest);
        }
        apiRequest.setVendor(Vendor.EQUIFAX);
    }

    private void handleResponseBodyCreditRequestDto(CreditRequestDto creditRequestDto) {
        APIRequest apiRequest = apiRequestData.get();
        if (apiRequest == null) {
            logger.warn("Creating new thread local APIRequest for CreditRequestDto. This is not what should happen.");
            apiRequest = new APIRequest();
            apiRequestData.set(apiRequest);
        }
        apiRequest.setCreditRequestId(creditRequestDto.getId());
        apiRequest.setSource(creditRequestDto.getSource());
    }

    @Async
    @Transactional
    public void saveAsync(APIRequest apiRequest) {
        logger.info("Saving APIRequest to MongoDB");
        apiRequestRepository.save(apiRequest);
    }

    public void setRequestBody(Object requestBody) {
        logger.info("Request body:\n{}", requestBody);
        APIRequest apiRequest = apiRequestData.get();
        if (apiRequestData == null) {
            logger.warn("Creating new thread local APIRequest for response body. This is not what should happen.");
            apiRequest = new APIRequest();
            apiRequestData.set(apiRequest);
        }
        setRequestBodyMetadata(requestBody);
        apiRequest.setRequestBody(new RequestResponsePayload(requestBody.getClass().getName(), requestBody));
    }

    private void setRequestBodyMetadata(Object requestBody) {
        if (EquifaxRequestDto.class == requestBody.getClass()) {
            handleRequestBodyEquifaxRequestDto((EquifaxRequestDto) requestBody);

        } else {
            logger.warn("Unable to set request body metadata for class {}", requestBody.getClass());
        }
    }

    public void setRequestHeaders(Map<String, String> requestHeaders) {
        logger.info("Request headers:\n{}", requestHeaders);
        APIRequest apiRequest = apiRequestData.get();
        if (apiRequest == null) {
            logger.info("Creating new thread local APIRequest for request headers");
            apiRequest = new APIRequest();
            apiRequestData.set(apiRequest);
        }
        apiRequest.setRequestDate(LocalDateTime.now());
        apiRequest.setRequestHeaders(requestHeaders);
    }

    public void setResponseBody(Object responseBody) {
        logger.info("Response body:\n{}", responseBody);
        APIRequest apiRequest = apiRequestData.get();
        if (apiRequestData == null) {
            logger.warn("Creating new thread local APIRequest for response body. This is not what should happen.");
            apiRequest = new APIRequest();
            apiRequestData.set(apiRequest);
        }
        setResponseBodyMetadata(responseBody);
        apiRequest.setResponseBody(new RequestResponsePayload(responseBody.getClass().getName(), responseBody));
    }

    private void setResponseBodyMetadata(Object responseBody) {
        if (CreditRequestDto.class == responseBody.getClass()) {
            handleResponseBodyCreditRequestDto((CreditRequestDto) responseBody);

        } else {
            logger.warn("Unable to set response body metadata for class {}", responseBody.getClass());
        }
    }

    public void setResponseHeaders(Map<String, String> responseHeaders) {
        logger.info("Response headers:\n{}", responseHeaders);
        APIRequest apiRequest = apiRequestData.get();
        if (apiRequestData == null) {
            logger.warn("Creating new thread local APIRequest for response body. This is not what should happen.");
            apiRequest = new APIRequest();
            apiRequestData.set(apiRequest);
        }
        apiRequest.setResponseHeaders(responseHeaders);
    }
}
