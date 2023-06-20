package com.crscreditapi.demo.unit.controller;

import com.crscreditapi.demo.controller.CreditController;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CreditControllerTests {

//    @Test
    public void testGetCreditRequestById() {
        CreditController creditController = new CreditController(null, null);
        assertEquals("Hello, World!", creditController.getCreditRequestById(1L));
    }
}
