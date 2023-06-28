package com.crscreditapi.demo.controller;

import com.crscreditapi.demo.dto.CRSRequestDto;
import com.crscreditapi.demo.enumeration.CreditRequestSource;
import com.crscreditapi.demo.enumeration.Vendor;
import com.crscreditapi.demo.mapper.CRSRequestMapper;
import com.crscreditapi.demo.service.CRSRequestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/crs-requests")
public class CRSRequestController {

    private final CRSRequestMapper crsRequestMapper;

    private final CRSRequestService crsRequestService;

    public CRSRequestController(CRSRequestMapper crsRequestMapper,
                                CRSRequestService crsRequestService) {
        this.crsRequestMapper = crsRequestMapper;
        this.crsRequestService = crsRequestService;
    }

    @GetMapping
    public List<CRSRequestDto> findAllByCriteria(@RequestParam(value = "creditRequestId", required = false) Long creditRequestId,
                                                            @RequestParam(value = "source", required = false) CreditRequestSource source,
                                                            @RequestParam(value = "startDate", required = false) String startDateStr,
                                                            @RequestParam(value = "endDate", required = false) String endDateStr,
                                                            @RequestParam(value = "vendor", required = false) Vendor vendor) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDate = startDateStr == null ? null : LocalDateTime.from(dtf.parse(startDateStr));
        LocalDateTime endDate = endDateStr == null ? null : LocalDateTime.from(dtf.parse(endDateStr));

        return crsRequestMapper.list(crsRequestService.findAllByCriteria(creditRequestId, source, startDate, endDate, vendor));
    }
}
