package com.BookYourCab.CarBookingApp.Services.Implementations;

import com.BookYourCab.CarBookingApp.Services.DistanceService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@Slf4j
public class DistanceOSRMImpl implements DistanceService {

    private static final String OSRM_API_BASE_URL =
            "http://router.project-osrm.org/route/v1/driving/";

    @Override
    public double calculateDistance(Point src, Point dest) {

//        todo add api link for OSRM
        if (src == null || dest == null) {
            log.error("Source or Destination is NULL! src={}, dest={}", src, dest);
            throw new IllegalArgumentException("Source or Destination point cannot be null");
        }
        log.info("Calculating distance from {} to {}", src, dest);

        try{
            String uri = OSRM_API_BASE_URL+src.getX()+","+src.getY()+";"+dest.getX()+","+dest.getY();
            OSRMResponseDto osrmResponseDto = RestClient.builder()
                    .baseUrl(OSRM_API_BASE_URL)
                    .build()
                    .get()
                    .uri(uri)
                    .retrieve()
                    .body(OSRMResponseDto.class);
            if (osrmResponseDto == null || osrmResponseDto.getRoutes().isEmpty()) {
                throw new RuntimeException("Invalid response from OSRM");
            }

            return osrmResponseDto.getRoutes().get(0).getDistance()/1000.0;
        } catch (Exception e) {
            throw new RuntimeException("Error getting data from OSRM "+e.getMessage());
        }

    }
}
@Data
class OSRMResponseDto{
    private List<OSRMRoute> routes;
}
@Data
class OSRMRoute{
    private Double distance;
}
