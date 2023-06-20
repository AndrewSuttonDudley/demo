package com.crscreditapi.demo.aspect;

import com.crscreditapi.demo.dto.CreditRequestDto;
import com.crscreditapi.demo.dto.crs.EquifaxRequestDto;
import com.crscreditapi.demo.enumeration.Vendor;
import com.crscreditapi.demo.model.mongo.ApiRequest;
import com.crscreditapi.demo.repository.mongo.ApiRequestRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Aspect
@Component
public class HttpRequestAspect {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestAspect.class);

    private final ApiRequestRepository apiRequestRepository;

    public HttpRequestAspect(ApiRequestRepository apiRequestRepository) {
        this.apiRequestRepository = apiRequestRepository;
    }

    @Around("execution(public * com.crscreditapi.demo.controller.CreditController.postCreditRequest(..))")
    public CreditRequestDto catchPostCreditRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("HttpRequestAspect::catchPostCreditRequest");
        EquifaxRequestDto request = (EquifaxRequestDto) joinPoint.getArgs()[0];
        Map<String, String> headers = (Map<String, String>) joinPoint.getArgs()[1];

        ObjectMapper mapper = new ObjectMapper();
        logger.info("request: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(request));
        logger.info("headers: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(headers));

        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setRequestHeaders(headers);
        apiRequest.setRequest(request);
        apiRequest.setRequestDate(java.time.LocalDateTime.now());
        apiRequest.setVendor(Vendor.EQUIFAX);
        apiRequest = apiRequestRepository.save(apiRequest);

        CreditRequestDto creditRequestDto = (CreditRequestDto) joinPoint.proceed();

        apiRequest.setCreditRequestId(creditRequestDto.getId());
        apiRequest.setResponse(creditRequestDto);
        apiRequest.setSource(creditRequestDto.getSource());
        apiRequestRepository.save(apiRequest);

        return creditRequestDto;
    }
}
