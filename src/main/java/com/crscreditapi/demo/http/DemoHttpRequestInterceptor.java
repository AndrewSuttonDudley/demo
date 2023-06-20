package com.crscreditapi.demo.http;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DemoHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        String bodyStr = new String(body, StandardCharsets.UTF_8);

        ClientHttpResponse response = execution.execute(request, body);
        response.getHeaders().add("Foo", "bar");
        return response;
    }
}
