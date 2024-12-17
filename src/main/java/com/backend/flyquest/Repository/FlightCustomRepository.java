package com.backend.flyquest.Repository;

import com.backend.flyquest.DTO.FlightDTO;
import com.backend.flyquest.Model.Flight;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightCustomRepository {
    public List<FlightDTO> findFlightByDepartureAndDestination(String departure, String destination);
}
