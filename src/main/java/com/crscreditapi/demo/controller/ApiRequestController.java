package com.crscreditapi.demo.controller;

import com.crscreditapi.demo.model.mongo.ApiRequest;
import com.crscreditapi.demo.service.ApiRequestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api-requests")
public class ApiRequestController {

    private final ApiRequestService apiRequestService;

    public ApiRequestController(ApiRequestService apiRequestService) {
        this.apiRequestService = apiRequestService;
    }

    @GetMapping
    public List<ApiRequest> findAll() {
        return apiRequestService.findAll();
    }
}
