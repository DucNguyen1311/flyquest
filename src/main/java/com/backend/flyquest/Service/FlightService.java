package com.backend.flyquest.Service;

import com.backend.flyquest.DTO.FlightDTO;
import com.backend.flyquest.Model.Flight;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FlightService {
    public void saveFlightToDatabase(Flight flight);
    public List<FlightDTO> getAllFlightsFromDepartureToDestination(String departure, String destination);
}
