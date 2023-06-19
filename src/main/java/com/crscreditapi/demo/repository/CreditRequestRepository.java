package com.crscreditapi.demo.repository;

import com.crscreditapi.demo.model.CreditRequest;

import java.util.Optional;

public interface CreditRequestRepository extends AbstractRepository<CreditRequest, Long> {

    Optional<CreditRequest> findByExternalId(String externalId);
}
