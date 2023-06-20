package com.crscreditapi.demo.repository;

import com.crscreditapi.demo.enumeration.CreditRequestStatus;
import com.crscreditapi.demo.model.mysql.CreditRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CreditRequestRepository extends AbstractRepository<CreditRequest, Long> {

    Optional<CreditRequest> findByPdfReportId(String pdfReportId);

    Page<CreditRequest> findAllBySsnAndStartDateGreaterThanAndStatus(String ssn, LocalDateTime startDate, CreditRequestStatus status, Pageable pageable);
}
