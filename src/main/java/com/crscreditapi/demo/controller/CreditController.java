package com.crscreditapi.demo.controller;

import com.crscreditapi.demo.dto.CreditRequestDto;
import com.crscreditapi.demo.dto.crs.EquifaxRequestDto;
import com.crscreditapi.demo.mapper.CreditRequestMapper;
import com.crscreditapi.demo.service.CreditRequestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit")
public class CreditController {

    private final CreditRequestMapper creditRequestMapper;

    private final CreditRequestService creditRequestService;

    public CreditController(CreditRequestMapper creditRequestMapper,
                            CreditRequestService creditRequestService) {
        this.creditRequestMapper = creditRequestMapper;
        this.creditRequestService = creditRequestService;
    }

    @GetMapping("/requests/{id}")
    public CreditRequestDto getCreditRequestById(@PathVariable("id") Long id) {
        return creditRequestMapper.map(creditRequestService.findCreditRequestById(id));
    }

    @PostMapping("/requests")
    public CreditRequestDto postCreditRequest(@RequestBody EquifaxRequestDto request) {
        return creditRequestMapper.map(creditRequestService.submitCreditRequest(request));
    }
}
