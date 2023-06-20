package com.crscreditapi.demo.controller;

import com.crscreditapi.demo.dto.CreditRequestDto;
import com.crscreditapi.demo.dto.crs.EquifaxRequestDto;
import com.crscreditapi.demo.mapper.CreditRequestMapper;
import com.crscreditapi.demo.service.CreditRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @GetMapping("/requests")
    public CreditRequestDto getCreditRequests(@RequestParam(value = "pdfReportId", required = false) String pdfReportId) {
        return creditRequestMapper.map(creditRequestService.findCreditRequestByPdfReportId(pdfReportId));
    }

    @PostMapping("/requests")
    public CreditRequestDto postCreditRequest(@RequestBody EquifaxRequestDto request,
                                              @RequestHeader Map<String, String> headers) {
        return creditRequestMapper.map(creditRequestService.submitCreditRequest(request));
    }
}
