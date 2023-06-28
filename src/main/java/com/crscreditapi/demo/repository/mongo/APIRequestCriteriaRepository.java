package com.crscreditapi.demo.repository.mongo;

import com.crscreditapi.demo.enumeration.CreditRequestSource;
import com.crscreditapi.demo.enumeration.Vendor;
import com.crscreditapi.demo.model.mongo.APIRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface APIRequestCriteriaRepository {

    List<APIRequest> findAllByCriteria(Long creditRequestId, CreditRequestSource source, LocalDateTime startDate, LocalDateTime endDate, Vendor vendor);
}
