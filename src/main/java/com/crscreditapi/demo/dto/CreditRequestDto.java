package com.crscreditapi.demo.dto;

import com.crscreditapi.demo.enumeration.CreditRequestSource;
import com.crscreditapi.demo.enumeration.CreditRequestStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class CreditRequestDto extends AbstractDto {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    private String pdfReportId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime requestDate;

    private CreditRequestSource source;

    private String ssn;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

   private CreditRequestStatus status;

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getPdfReportId() {
        return pdfReportId;
    }

    public void setPdfReportId(String pdfReportId) {
        this.pdfReportId = pdfReportId;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public CreditRequestSource getSource() {
        return source;
    }

    public void setSource(CreditRequestSource source) {
        this.source = source;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public CreditRequestStatus getStatus() {
        return status;
    }

    public void setStatus(CreditRequestStatus status) {
        this.status = status;
    }
}
