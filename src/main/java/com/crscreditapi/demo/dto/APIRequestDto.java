package com.crscreditapi.demo.dto;

import com.crscreditapi.demo.dto.crs.EquifaxRequestDto;
import com.crscreditapi.demo.enumeration.CreditRequestSource;
import com.crscreditapi.demo.enumeration.Vendor;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Map;

public class APIRequestDto {

    private String id;

    private Long creditRequestId;

    private Map<String, String> requestHeaders;

    private EquifaxRequestDto request;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime requestDate;

    private CreditRequestDto response;

    private CreditRequestSource source;

    private Vendor vendor;

    public APIRequestDto() {
    }

    public APIRequestDto(String id,
                         Long creditRequestId,
                         Map<String, String> requestHeaders,
                         EquifaxRequestDto request,
                         LocalDateTime requestDate,
                         CreditRequestDto response,
                         CreditRequestSource source,
                         Vendor vendor) {
        this.id = id;
        this.creditRequestId = creditRequestId;
        this.requestHeaders = requestHeaders;
        this.request = request;
        this.requestDate = requestDate;
        this.response = response;
        this.source = source;
        this.vendor = vendor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCreditRequestId() {
        return creditRequestId;
    }

    public void setCreditRequestId(Long creditRequestId) {
        this.creditRequestId = creditRequestId;
    }

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(Map<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public EquifaxRequestDto getRequest() {
        return request;
    }

    public void setRequest(EquifaxRequestDto request) {
        this.request = request;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public CreditRequestDto getResponse() {
        return response;
    }

    public void setResponse(CreditRequestDto response) {
        this.response = response;
    }

    public CreditRequestSource getSource() {
        return source;
    }

    public void setSource(CreditRequestSource source) {
        this.source = source;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}
