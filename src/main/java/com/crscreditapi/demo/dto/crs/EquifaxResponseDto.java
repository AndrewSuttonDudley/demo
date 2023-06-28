package com.crscreditapi.demo.dto.crs;

public class EquifaxResponseDto {

    private Long creditRequestId;

    private String pdfReportId;

    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
