package com.crscreditapi.demo.repository.mongo;

import com.crscreditapi.demo.enumeration.Vendor;
import com.crscreditapi.demo.model.mongo.CRSRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;
import java.util.List;

public class CRSRequestCriteriaRepositoryImpl implements CRSRequestCriteriaRepository{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<CRSRequest> findAllByCriteria(Long creditRequestId, LocalDateTime startDate, LocalDateTime endDate, Vendor vendor) {
        Query query = new Query();

        if (creditRequestId != null) {
            query.addCriteria(Criteria.where("creditRequestId").is(creditRequestId));
        }

        if (startDate != null) {
            query.addCriteria(Criteria.where("requestDate").gte(startDate));
        }

        if (endDate != null) {
            query.addCriteria(Criteria.where("requestDate").lte(endDate));
        }

        if (vendor != null) {
            query.addCriteria(Criteria.where("vendor").is(vendor));
        }

        return mongoTemplate.find(query, CRSRequest.class);
    }
}
