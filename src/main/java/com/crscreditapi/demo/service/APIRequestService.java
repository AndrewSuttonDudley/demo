package com.crscreditapi.demo.service;

import com.crscreditapi.demo.dto.APIRequestDto;
import com.crscreditapi.demo.enumeration.CreditRequestSource;
import com.crscreditapi.demo.enumeration.Vendor;
import com.crscreditapi.demo.model.mongo.APIRequest;
import com.crscreditapi.demo.repository.mongo.APIRequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class APIRequestService {

    private final APIRequestRepository apiRequestRepository;

    public APIRequestService(APIRequestRepository apiRequestRepository) {
        this.apiRequestRepository = apiRequestRepository;
    }

    public List<APIRequest> findAll() {
        return apiRequestRepository.findAll();
    }

//    public List<APIRequestDto> findAllByCriteria(Long creditRequestId, CreditRequestSource source, LocalDateTime startDate, LocalDateTime endDate, Vendor vendor) {
//        return apiRequestRepository.findAllByCriteria(creditRequestId, source, startDate, endDate, vendor);
//    }
}
