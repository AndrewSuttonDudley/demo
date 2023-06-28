package com.crscreditapi.demo.model.mongo;

import com.crscreditapi.demo.enumeration.CreditRequestSource;
import com.crscreditapi.demo.enumeration.Vendor;
import com.crscreditapi.demo.model.BaseModel;
import com.crscreditapi.demo.model.mongo.sub.RequestResponsePayload;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "apiRequests")
public class APIRequest extends BaseModel {

    @Id
    private String id;

    @Field
    private Long creditRequestId;

    private RequestResponsePayload requestBody;

    @Field
    private LocalDateTime requestDate;

    private Map<String, String> requestHeaders;

    private RequestResponsePayload responseBody;

    private Map<String, String> responseHeaders;

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
