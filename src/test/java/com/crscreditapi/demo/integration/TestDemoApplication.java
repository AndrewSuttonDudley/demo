package com.crscreditapi.demo.integration;

import com.crscreditapi.demo.DemoApplication;
import org.springframework.boot.SpringApplication;
import org.testcontainers.containers.MySQLContainer;

//@TestConfiguration(proxyBeanMethods = false)
public class TestDemoApplication {

//    @Bean
//    @ServiceConnection
    static MySQLContainer<?> mysqlContainer() {
        return new MySQLContainer<>("mysql:8.0.33")
                .withDatabaseName("demo")
                .withUsername("demo")
                .withPassword("demo");
    }

    public static void main(String[] args) {
        SpringApplication.from(DemoApplication::main).with(TestDemoApplication.class).run(args);
    }
}
