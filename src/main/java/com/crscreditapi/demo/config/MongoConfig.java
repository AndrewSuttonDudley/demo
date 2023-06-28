package com.crscreditapi.demo.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.ConnectionPoolSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.concurrent.TimeUnit;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${MONGO_DATABASE}")
    private String database;

    @Value("${MONGO_URI}")
    private String uri;

    @Bean
    @Override
    public MongoClient mongoClient() {
        final ConnectionString connectionString = new ConnectionString(uri);
        final MongoClientSettings.Builder mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
                .applyToConnectionPoolSettings(builder -> builder.applySettings(connectionPoolSettings()));
        return MongoClients.create(mongoClientSettings.build());
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(), database);
    }

    private ConnectionPoolSettings connectionPoolSettings() {
        return ConnectionPoolSettings.builder()
                .maxSize(50)
                .maxWaitTime(20, TimeUnit.SECONDS)
                .maxConnectionIdleTime(20, TimeUnit.SECONDS)
                .maxConnectionLifeTime(60, TimeUnit.SECONDS).build();
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }
}
