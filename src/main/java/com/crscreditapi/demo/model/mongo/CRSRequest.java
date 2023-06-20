package com.crscreditapi.demo.model.mongo;

import com.crscreditapi.demo.dto.crs.EquifaxRequestDto;
import com.crscreditapi.demo.dto.crs.EquifaxResponseDto;
import com.crscreditapi.demo.enumeration.Vendor;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.http.HttpHeaders;

import java.time.LocalDateTime;

@Document(collection = "crsRequests")
public class CRSRequest {

    @Id
    private String id;

    @Field
    private Long creditRequestId;

    private HttpHeaders requestHeaders;

    private EquifaxRequestDto requestBody;

    @Field
    private LocalDateTime requestDate;

    private EquifaxResponseDto responseBody;

    private HttpHeaders responseHeaders;

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

    public HttpHeaders getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(HttpHeaders requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public EquifaxRequestDto getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(EquifaxRequestDto requestBody) {
        this.requestBody = requestBody;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public EquifaxResponseDto getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(EquifaxResponseDto responseBody) {
        this.responseBody = responseBody;
    }

    public HttpHeaders getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(HttpHeaders responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}
