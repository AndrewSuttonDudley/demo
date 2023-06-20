package com.crscreditapi.demo.model.mongo;

import com.crscreditapi.demo.dto.CreditRequestDto;
import com.crscreditapi.demo.dto.crs.EquifaxRequestDto;
import com.crscreditapi.demo.enumeration.CreditRequestSource;
import com.crscreditapi.demo.enumeration.Vendor;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "apiRequests")
public class ApiRequest {

    @Id
    private String id;

    @Field
    private Long creditRequestId;

    private Map<String, String> requestHeaders;

    private EquifaxRequestDto request;

    @Field
    private LocalDateTime requestDate;

    private CreditRequestDto response;

    @Field
    private CreditRequestSource source;

    @Field
    @Enumerated(EnumType.STRING)
    private Vendor vendor;

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
