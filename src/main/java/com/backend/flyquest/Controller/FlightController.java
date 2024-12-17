package com.backend.flyquest.Controller;

import com.backend.flyquest.Model.Flight;
import com.backend.flyquest.Payload.FlightRequest;
import com.backend.flyquest.Service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;
    @PostMapping("/save")
    public ResponseEntity<?> saveFlight(@RequestBody FlightRequest flightRequest) {
        try {
            flightService.saveFlightToDatabase(new Flight(flightRequest));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/savemany")
    public ResponseEntity<?> saveManyFlights(@RequestBody List<FlightRequest> flightRequestList) {
        try {
            for (FlightRequest flightRequest : flightRequestList) {
                flightService.saveFlightToDatabase(new Flight(flightRequest));
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
