package com.crscreditapi.demo.model.mysql;

import com.crscreditapi.demo.enumeration.CreditRequestSource;
import com.crscreditapi.demo.enumeration.CreditRequestStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "credit_requests")
public class CreditRequest extends AbstractMySQLModel {

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "pdf_report_id")
    private String pdfReportId;

    @Column(name = "request_date")
    private LocalDateTime requestDate;

    @Enumerated(EnumType.STRING)
    private CreditRequestSource source;

    private String ssn;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Enumerated(EnumType.STRING)
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
