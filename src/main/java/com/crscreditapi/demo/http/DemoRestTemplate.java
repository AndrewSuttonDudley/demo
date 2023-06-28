package com.crscreditapi.demo.http;

import com.crscreditapi.demo.config.AppConfig;
import com.crscreditapi.demo.dto.crs.EquifaxRequestDto;
import com.crscreditapi.demo.enumeration.Vendor;
import com.crscreditapi.demo.model.mongo.CRSRequest;
import com.crscreditapi.demo.model.mongo.sub.RequestResponsePayload;
import com.crscreditapi.demo.repository.mongo.CRSRequestRepository;
import com.crscreditapi.demo.util.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

public class DemoRestTemplate extends RestTemplate {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DemoRestTemplate.class);

    @Override
    public <T> ResponseEntity<T> exchange(String url,
                                          HttpMethod method,
                                          @Nullable HttpEntity<?> requestEntity,
                                          Class<T> responseType,
                                          Map<String, ?> uriVariables) throws RestClientException {

        Object requestBody = requestEntity.getBody();

        CRSRequest crsRequest = new CRSRequest();

        if (requestBody != null) {
            crsRequest.setRequestBody(new RequestResponsePayload(requestBody.getClass().getName(), requestBody));
        }
        crsRequest.setRequestDate(LocalDateTime.now());
        crsRequest.setRequestHeaders(HttpUtil.getHeaders(requestEntity.getHeaders()));
        setRequestMetadata(crsRequest, requestBody);

        ResponseEntity<T> responseEntity = super.exchange(url, method, requestEntity, responseType, uriVariables);

        Object responseBody = responseEntity.getBody();

        if (responseBody != null) {
            crsRequest.setResponseBody(new RequestResponsePayload(responseBody.getClass().getName(), responseBody));
        }
        crsRequest.setResponseHeaders(HttpUtil.getHeaders(responseEntity.getHeaders()));

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            logger.info("Saving CRSRequest: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(crsRequest));
        } catch (Exception e) {
            logger.error("Shouldn't happen", e);
        }

        AppConfig.getBean(CRSRequestRepository.class).save(crsRequest);

        return super.exchange(url, method, requestEntity, responseType, uriVariables);
    }

    private void setRequestMetadata(CRSRequest crsRequest, Object requestBody) {
        if (requestBody instanceof EquifaxRequestDto) {
            handleResponseBodyEquifaxRequestDto(crsRequest, (EquifaxRequestDto) requestBody);

        } else {
            logger.warn("Unable to set request body metadata for class {}", requestBody.getClass());
        }
    }

    private void handleResponseBodyEquifaxRequestDto(CRSRequest crsRequest, EquifaxRequestDto equifaxRequestDto) {
        crsRequest.setCreditRequestId(equifaxRequestDto.getCreditRequestId());
        crsRequest.setVendor(Vendor.EQUIFAX);
    }
}
