package com.crscreditapi.demo.connector;

import com.crscreditapi.demo.dto.crs.AuthRequestDto;
import com.crscreditapi.demo.dto.crs.AuthResponseDto;
import com.crscreditapi.demo.dto.crs.EquifaxRequestDto;
import com.crscreditapi.demo.dto.crs.EquifaxResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CRSConnector extends AbstractConnector {

    private static final Logger logger = LoggerFactory.getLogger(CRSConnector.class);

    public AuthResponseDto authenticate(AuthRequestDto authRequestDto) {
        return post("/users/login", authRequestDto, AuthResponseDto.class, null, null);
    }

    private AuthRequestDto getAuthrequest() {
        return new AuthRequestDto("demo-user-account", "tryCRSfr33!");
    }

    @Override
    protected String getHost() {
        return "https://api-sandbox.stitchcredit.com/api";
    }


    @Async
    public CompletableFuture<EquifaxResponseDto> postEquifaxCreditReportRequest(EquifaxRequestDto requestBody) {
        AuthResponseDto authResponse = authenticate(getAuthrequest());
//        logger.info("Authenticated and received token: " + authResponse.getToken());

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authResponse.getToken());

        EquifaxResponseDto response = post("/equifax/credit-report", requestBody, EquifaxResponseDto.class, headers, null);
        response.setCreditRequestId(requestBody.getCreditRequestId());

        return CompletableFuture.completedFuture(response);
    }
}
