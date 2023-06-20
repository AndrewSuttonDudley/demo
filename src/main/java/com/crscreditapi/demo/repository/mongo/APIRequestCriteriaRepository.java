package com.crscreditapi.demo.repository.mongo;

import com.crscreditapi.demo.dto.APIRequestDto;
import com.crscreditapi.demo.enumeration.CreditRequestSource;
import com.crscreditapi.demo.enumeration.Vendor;

import java.time.LocalDateTime;
import java.util.List;

public interface APIRequestCriteriaRepository {

    List<APIRequestDto> findAllByCriteria(Long creditRequestId, CreditRequestSource source, LocalDateTime startDate, LocalDateTime endDate, Vendor vendor);
}
