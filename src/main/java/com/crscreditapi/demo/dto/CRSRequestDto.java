package com.crscreditapi.demo.dto;

import com.crscreditapi.demo.enumeration.Vendor;
import com.crscreditapi.demo.model.mongo.sub.RequestResponsePayload;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Map;

public class CRSRequestDto extends AbstractDto {

    private String id;

    private Long creditRequestId;

    private RequestResponsePayload requestBody;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime requestDate;

    private Map<String, String> requestHeaders;

    private RequestResponsePayload responseBody;

    private Map<String, String> responseHeaders;

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

    public RequestResponsePayload getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(RequestResponsePayload requestBody) {
        this.requestBody = requestBody;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(Map<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public RequestResponsePayload getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(RequestResponsePayload responseBody) {
        this.responseBody = responseBody;
    }

    public Map<String, String> getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(Map<String, String> responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}
