package com.backend.flyquest.Service;

import com.backend.flyquest.Model.Flight;
import org.springframework.stereotype.Service;

@Service
public interface FlightService {
    public void saveFlightToDatabase(Flight flight);
}
