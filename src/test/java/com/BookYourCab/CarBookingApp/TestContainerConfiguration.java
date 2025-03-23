package com.BookYourCab.CarBookingApp;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration
@Slf4j
public class TestContainerConfiguration {
    @Bean
    @ServiceConnection
     PostgreSQLContainer<?> postgreSQLContainer(){
        var image = DockerImageName.parse("postgis/postgis:12-3.0")
                .asCompatibleSubstituteFor("postgres");
        log.info("Test container image created successfully!");
        return new PostgreSQLContainer<>(image);
    }

}

