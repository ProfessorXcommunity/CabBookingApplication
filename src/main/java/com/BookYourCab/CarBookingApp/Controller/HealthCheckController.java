package com.BookYourCab.CarBookingApp.Controller;

import com.BookYourCab.CarBookingApp.Advices.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.time.LocalDateTime;

@RestController
@Slf4j
public class HealthCheckController {
    @GetMapping("/")
    public ResponseEntity<ApiResponse<String>> healthCheckController() {
        log.info("Health Check endpoint Hit");
        ApiResponse<String> response = ApiResponse.<String>builder()
                .timeStamp(LocalDateTime.now())
                .data("OK")
                .error(null)
                .build();

        return ResponseEntity.ok(response);
    }
}
