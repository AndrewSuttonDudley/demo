package com.crscreditapi.demo.aspect;

import com.crscreditapi.demo.dto.CreditRequestDto;
import com.crscreditapi.demo.dto.crs.EquifaxRequestDto;
import com.crscreditapi.demo.dto.crs.EquifaxResponseDto;
import com.crscreditapi.demo.enumeration.Vendor;
import com.crscreditapi.demo.model.mongo.APIRequest;
import com.crscreditapi.demo.model.mongo.CRSRequest;
import com.crscreditapi.demo.repository.mongo.APIRequestRepository;
import com.crscreditapi.demo.repository.mongo.CRSRequestRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Aspect
@Component
public class HttpRequestAspect {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestAspect.class);

    private final APIRequestRepository apiRequestRepository;

    private final CRSRequestRepository crsRequestRepository;

    public HttpRequestAspect(APIRequestRepository apiRequestRepository,
                             CRSRequestRepository crsRequestRepository) {
        this.apiRequestRepository = apiRequestRepository;
        this.crsRequestRepository = crsRequestRepository;
    }

    @Around("execution(public * com.crscreditapi.demo.controller.CreditController.postCreditRequest(..))")
    public CreditRequestDto catchPostCreditRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("HttpRequestAspect::catchPostCreditRequest");
        EquifaxRequestDto request = (EquifaxRequestDto) joinPoint.getArgs()[0];
        Map<String, String> headers = (Map<String, String>) joinPoint.getArgs()[1];

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        logger.info("API Request Headers: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(headers));
        logger.info("API Request Body: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(request));

        APIRequest apiRequest = new APIRequest();
        apiRequest.setRequestHeaders(headers);
        apiRequest.setRequest(request);
        apiRequest.setRequestDate(java.time.LocalDateTime.now());
        apiRequest.setVendor(Vendor.EQUIFAX);
        apiRequest = apiRequestRepository.save(apiRequest);

        CreditRequestDto creditRequestDto = (CreditRequestDto) joinPoint.proceed();

        logger.info("API Response Body: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(creditRequestDto));

        apiRequest.setCreditRequestId(creditRequestDto.getId());
        apiRequest.setResponse(creditRequestDto);
        apiRequest.setSource(creditRequestDto.getSource());
        apiRequestRepository.save(apiRequest);

        return creditRequestDto;
    }

    @Around("execution(public * org.springframework.web.client.RestTemplate.exchange(..))")
    public <T> ResponseEntity<T> catchRestTemplateExchange(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("HttpRequestAspect::catchRestTemplateExchange");
        String url = (String) joinPoint.getArgs()[0];
        HttpEntity<?> requestEntity = (HttpEntity<?>) joinPoint.getArgs()[2];

        HttpHeaders headers = requestEntity.getHeaders();
        EquifaxRequestDto requestBody = (EquifaxRequestDto) requestEntity.getBody();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        logger.info("CRS Request Headers: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(headers));
        logger.info("CRS Request Body: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestBody));

        CRSRequest crsRequest = new CRSRequest();
        crsRequest.setCreditRequestId(requestBody.getCreditRequestId());
        crsRequest.setRequestHeaders(headers);
        crsRequest.setRequestBody(requestBody);
        crsRequest.setRequestDate(java.time.LocalDateTime.now());
        crsRequest.setVendor(Vendor.EQUIFAX);
        crsRequest = crsRequestRepository.save(crsRequest);

        ResponseEntity<T> responseEntity = (ResponseEntity<T>) joinPoint.proceed();

        logger.info("CRS Response Headers: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseEntity.getHeaders()));
        logger.info("CRS Response Body: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseEntity.getBody()));

        crsRequest.setResponseBody((EquifaxResponseDto) responseEntity.getBody());
        crsRequest.setResponseHeaders(responseEntity.getHeaders());
        crsRequestRepository.save(crsRequest);

        return responseEntity;
    }
}
