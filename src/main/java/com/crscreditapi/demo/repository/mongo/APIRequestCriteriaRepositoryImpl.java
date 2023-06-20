package com.crscreditapi.demo.repository.mongo;

import com.crscreditapi.demo.dto.APIRequestDto;
import com.crscreditapi.demo.enumeration.CreditRequestSource;
import com.crscreditapi.demo.enumeration.Vendor;
import com.crscreditapi.demo.model.mongo.APIRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class APIRequestCriteriaRepositoryImpl implements APIRequestCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<APIRequestDto> findAllByCriteria(Long creditRequestId, CreditRequestSource source, LocalDateTime startDate, LocalDateTime endDate, Vendor vendor) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        Long count = count(creditRequestId, source, startDate, endDate, vendor, cb);

        CriteriaQuery<APIRequestDto> query = cb.createQuery(APIRequestDto.class);
        Root<APIRequest> root = query.from(APIRequest.class);

        List<Predicate> predicates = preparePredicates(creditRequestId, source, startDate, endDate, vendor, cb, root);

        query.multiselect(
                    root.get("id"),
                    root.get("creditRequestId"),
                    root.get("requestHeaders"),
                    root.get("request"),
                    root.get("requestDate"),
                    root.get("response"),
                    root.get("source"),
                    root.get("vendor"))
                .where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(query).getResultList();
    }

    private Long count(Long creditRequestId, CreditRequestSource source, LocalDateTime startDate, LocalDateTime endDate, Vendor vendor, CriteriaBuilder cb) {
        CriteriaQuery<Long> query = cb.createQuery(Long.class);

        Root<APIRequest> root = query.from(APIRequest.class);

        List<Predicate> predicates = preparePredicates(creditRequestId, source, startDate, endDate, vendor, cb, root);

        query.where(predicates.toArray(new Predicate[predicates.size()]));
        query.select(cb.count(root));

        return entityManager.createQuery(query).getSingleResult();
    }

    List<Predicate> preparePredicates(Long creditRequestId, CreditRequestSource source, LocalDateTime startDate, LocalDateTime endDate, Vendor vendor, CriteriaBuilder cb, Root<APIRequest> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (creditRequestId != null) {
            predicates.add(cb.equal(root.get("creditRequestId"), creditRequestId));
        }

        if (source != null) {
            predicates.add(cb.equal(root.get("source"), source));
        }

        if (startDate != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("requestDate"), startDate));
        }

        if (endDate != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("requestDate"), endDate));
        }

        if (vendor != null) {
            predicates.add(cb.equal(root.get("vendor"), vendor));
        }

        return predicates;
    }
}
