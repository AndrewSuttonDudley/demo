package com.crscreditapi.demo.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;

public class HttpUtil {

    public static Map<String, String> getHeaders(HttpHeaders httpHeaders) {
        Map<String, String> headers = new HashMap<>();
        httpHeaders.forEach((key, value) -> headers.put(key, value.get(0)));
        return headers;
    }

    public static Map<String, String> getHeaders(HttpServletRequest httpRequest) {
        Map<String, String> headers = new HashMap<>();
        httpRequest.getHeaderNames()
                .asIterator()
                .forEachRemaining(headerName -> headers.put(headerName, httpRequest.getHeader(headerName)));
        return headers;
    }

    public static Map<String, String> getHeaders(HttpServletResponse httpRequest) {
        Map<String, String> headers = new HashMap<>();
        httpRequest.getHeaderNames()
                .stream()
                .forEach(headerName -> headers.put(headerName, httpRequest.getHeader(headerName)));
        return headers;
    }
}
