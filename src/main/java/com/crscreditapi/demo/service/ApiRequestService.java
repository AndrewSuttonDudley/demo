package com.crscreditapi.demo.service;

import com.crscreditapi.demo.model.mongo.ApiRequest;
import com.crscreditapi.demo.repository.mongo.ApiRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiRequestService {

    private final ApiRequestRepository apiRequestRepository;

    public ApiRequestService(ApiRequestRepository apiRequestRepository) {
        this.apiRequestRepository = apiRequestRepository;
    }

    public List<ApiRequest> findAll() {
        return apiRequestRepository.findAll();
    }
}
