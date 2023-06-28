package com.crscreditapi.demo.http;

import com.crscreditapi.demo.model.mongo.APIRequest;
import com.crscreditapi.demo.service.APIRequestService;
import com.crscreditapi.demo.util.HttpUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class DemoFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(DemoFilter.class);

    private final APIRequestService apiRequestService;

    public DemoFilter(APIRequestService apiRequestService) {
        this.apiRequestService = apiRequestService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Set API request headers
        apiRequestService.setRequestHeaders(HttpUtil.getHeaders((HttpServletRequest) request));

        // Continue execution of the filter chain and controller method
        chain.doFilter(request, response);

        // Set API response headers
        apiRequestService.setResponseHeaders(HttpUtil.getHeaders((HttpServletResponse) response));

        // This is the end of the API request cycle, so grab the APIRequest object from the thread local and save it to MongoDB asynchronously
        APIRequest apiRequest = apiRequestService.getThreadLocalAPIRequest();
        apiRequestService.saveAsync(apiRequest);
    }
}
