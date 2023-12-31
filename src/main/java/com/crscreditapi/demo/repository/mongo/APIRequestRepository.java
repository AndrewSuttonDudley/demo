package com.crscreditapi.demo.repository.mongo;

import com.crscreditapi.demo.model.mongo.APIRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface APIRequestRepository extends MongoRepository<APIRequest, String>, APIRequestCriteriaRepository {

    List<APIRequest> findAllByCreditRequestId(Long creditRequestId);
}
