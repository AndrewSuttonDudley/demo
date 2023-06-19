package com.crscreditapi.demo.service;

import com.crscreditapi.demo.model.CreditRequest;
import com.crscreditapi.demo.repository.CreditRequestRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CreditRequestService {

    private final CreditRequestRepository creditRequestRepository;

    public CreditRequestService(CreditRequestRepository creditRequestRepository) {
        this.creditRequestRepository = creditRequestRepository;
    }

    public CreditRequest findCreditRequestByExternalId(String externalId) {
        return creditRequestRepository.findByExternalId(externalId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CreditRequest not found"));
    }

    public CreditRequest findCreditRequestById(Long id) {
        return creditRequestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CreditRequest not found"));
    }
}
