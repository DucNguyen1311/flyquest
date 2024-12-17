package com.backend.flyquest.Controller;

import com.backend.flyquest.DTO.FlightDTO;
import com.backend.flyquest.Model.Flight;
import com.backend.flyquest.Payload.FlightRequest;
import com.backend.flyquest.Payload.GetFlightRequest;
import com.backend.flyquest.Service.FlightService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/getflight")
    public ResponseEntity<?> getFlight(@RequestBody GetFlightRequest flightRequest) {
        try {
            List<FlightDTO> flightDTOS= flightService.getAllFlightsFromDepartureToDestination(flightRequest.getDepartureid(), flightRequest.getDestinationid());

            return ResponseEntity.ok().body(flightDTOS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
