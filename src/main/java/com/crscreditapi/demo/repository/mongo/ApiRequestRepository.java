package com.crscreditapi.demo.repository.mongo;

import com.crscreditapi.demo.model.mongo.ApiRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApiRequestRepository extends MongoRepository<ApiRequest, String> {
}
