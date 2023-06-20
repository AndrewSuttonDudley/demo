package com.crscreditapi.demo.repository.mongo;

import com.crscreditapi.demo.model.mongo.CRSRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CRSRequestRepository extends MongoRepository<CRSRequest, String> {

    CRSRequest findByCreditRequestId(Long creditRequestId);
}
