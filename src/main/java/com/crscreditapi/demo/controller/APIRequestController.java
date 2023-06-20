package com.crscreditapi.demo.controller;

import com.crscreditapi.demo.dto.APIRequestDto;
import com.crscreditapi.demo.enumeration.CreditRequestSource;
import com.crscreditapi.demo.enumeration.Vendor;
import com.crscreditapi.demo.service.APIRequestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api-requests")
public class APIRequestController {

    private final APIRequestService apiRequestService;

    public APIRequestController(APIRequestService apiRequestService) {
        this.apiRequestService = apiRequestService;
    }

    @GetMapping
    public List<APIRequestDto> findAllByCriteria(@RequestParam(value = "creditRequestId", required = false) Long creditRequestId,
                                                 @RequestParam(value = "source", required = false) CreditRequestSource source,
                                                 @RequestParam(value = "startDate", required = false) String startDateStr,
                                                 @RequestParam(value = "endDate", required = false) String endDateStr,
                                                 @RequestParam(value = "vendor", required = false) Vendor vendor) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDate = startDateStr == null ? null : LocalDateTime.from(dtf.parse(startDateStr));
        LocalDateTime endDate = startDateStr == null ? null : LocalDateTime.from(dtf.parse(endDateStr));

        return apiRequestService.findAllByCriteria(creditRequestId, source, startDate, endDate, vendor);
    }
}
