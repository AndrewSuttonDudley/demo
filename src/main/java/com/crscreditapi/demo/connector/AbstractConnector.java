package com.crscreditapi.demo.connector;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractConnector {

    private static final Logger logger = LoggerFactory.getLogger(AbstractConnector.class);

    protected abstract String getHost();

    protected <T> T get(String path, Class<T> clazz, Map<String, String> uriVariables) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<T> entity = new HttpEntity<>(null, headers);

        ResponseEntity<T> response = restTemplate.exchange(getHost() + path, HttpMethod.GET, entity, clazz, uriVariables);
        return response.getBody();
    }

    protected <T> T post(String path, Object requestBody, Class<T> clazz, HttpHeaders headers, Map<String, String> uriVariables) {
        RestTemplate restTemplate = new RestTemplate();

        if (headers == null) {
            headers = new HttpHeaders();
        }
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        if (uriVariables == null) {
            uriVariables = new HashMap<>();
        }

        HttpEntity<Object> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<T> response;
        try {
            response = restTemplate.exchange(getHost() + path, HttpMethod.POST, entity, clazz, uriVariables);
        } catch (HttpClientErrorException.Unauthorized e) {
            logger.info("Couldn't post credit request", e);
            throw new RuntimeException("Request unauthorized: " + getHost() + path + " with body " + requestBody, e);
        }
        return response.getBody();
    }

    private MultiValueMap<String, String> getHeaders(Map<String, String> headerMap) {
        MultiValueMap<String, String> headers = new HttpHeaders();
        if (headerMap != null) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        return headers;
    }
}
