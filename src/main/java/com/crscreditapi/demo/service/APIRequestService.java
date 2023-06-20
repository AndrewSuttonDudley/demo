package com.crscreditapi.demo.service;

import com.crscreditapi.demo.model.mongo.APIRequest;
import com.crscreditapi.demo.repository.mongo.APIRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class APIRequestService {

    private static final Logger logger = LoggerFactory.getLogger(APIRequestService.class);

    private final APIRequestRepository apiRequestRepository;

    public APIRequestService(APIRequestRepository apiRequestRepository) {
        this.apiRequestRepository = apiRequestRepository;
    }

    public List<APIRequest> findAll() {
        List<APIRequest> apiRequests = apiRequestRepository.findAll();
        logger.info("apiRequestRepository.findAll() returned {} APIRequests", apiRequests.size());
        return apiRequests;
    }

//    public List<APIRequestDto> findAllByCriteria(Long creditRequestId, CreditRequestSource source, LocalDateTime startDate, LocalDateTime endDate, Vendor vendor) {
//        return apiRequestRepository.findAllByCriteria(creditRequestId, source, startDate, endDate, vendor);
//    }
}
