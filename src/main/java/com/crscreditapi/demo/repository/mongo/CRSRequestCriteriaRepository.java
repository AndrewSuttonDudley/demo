package com.crscreditapi.demo.repository.mongo;

import com.crscreditapi.demo.enumeration.Vendor;
import com.crscreditapi.demo.model.mongo.CRSRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface CRSRequestCriteriaRepository {

    List<CRSRequest> findAllByCriteria(Long creditRequestId, LocalDateTime startDate, LocalDateTime endDate, Vendor vendor);
}
