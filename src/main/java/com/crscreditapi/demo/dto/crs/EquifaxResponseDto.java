package com.crscreditapi.demo.dto.crs;

import com.fasterxml.jackson.databind.JsonNode;

public class EquifaxResponseDto {

    private Long creditRequestId;

    private String pdfReportId;

    private JsonNode data;

    public Long getCreditRequestId() {
        return creditRequestId;
    }

    public void setCreditRequestId(Long creditRequestId) {
        this.creditRequestId = creditRequestId;
    }

    public String getPdfReportId() {
        return pdfReportId;
    }

    public void setPdfReportId(String pdfReportId) {
        this.pdfReportId = pdfReportId;
    }

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }
}
