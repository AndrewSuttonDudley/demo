package com.crscreditapi.demo.integration.controller;

import com.crscreditapi.demo.controller.CreditController;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

@ActiveProfiles("test")
@SpringBootTest
@Testcontainers
public class CreditControllerIntegrationTests {

    @Autowired
    private CreditController creditController;

    @Test
    public void testGetCreditRequestById() {
        assertEquals("Hello, World!", creditController.getCreditRequestById(1L));
    }
}
