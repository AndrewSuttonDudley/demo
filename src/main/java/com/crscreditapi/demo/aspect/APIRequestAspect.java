package com.crscreditapi.demo.aspect;

import com.crscreditapi.demo.repository.mongo.APIRequestRepository;
import com.crscreditapi.demo.repository.mongo.CRSRequestRepository;
import com.crscreditapi.demo.service.APIRequestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.annotation.Annotation;

@Aspect
@Component
public class APIRequestAspect {

    private static final Logger logger = LoggerFactory.getLogger(APIRequestAspect.class);

    private final APIRequestRepository apiRequestRepository;

    private final APIRequestService apiRequestService;

    private final CRSRequestRepository crsRequestRepository;

    public APIRequestAspect(APIRequestRepository apiRequestRepository,
                            APIRequestService apiRequestService,
                            CRSRequestRepository crsRequestRepository) {
        this.apiRequestRepository = apiRequestRepository;
        this.apiRequestService = apiRequestService;
        this.crsRequestRepository = crsRequestRepository;
    }

    @Around("execution(public * com.crscreditapi.demo.controller.*.*(..)) && @annotation(postMapping)")
    public Object catchPostRequests(ProceedingJoinPoint joinPoint, PostMapping postMapping) throws Throwable {
        logger.info("APIRequestAspect::catchPostCreditRequest");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        Integer requestBodyIndex = getRequestBodyIndex(joinPoint);
        if (requestBodyIndex != null) {
            Object requestBody = joinPoint.getArgs()[requestBodyIndex];
            logger.info("API Request Body: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestBody));
            apiRequestService.setRequestBody(requestBody);
        }

        Object responseBody = joinPoint.proceed();

        logger.info("API Response Body: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseBody));
        apiRequestService.setResponseBody(responseBody);

        return responseBody;
    }

    private Integer getRequestBodyIndex(ProceedingJoinPoint joinPoint) {
        Annotation[][] annotations = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            if (annotations[i].length > 0) {
                if (annotations[i][0].annotationType() == RequestBody.class) {
                    return i;
                }
            }
        }
        return null;
    }
}
