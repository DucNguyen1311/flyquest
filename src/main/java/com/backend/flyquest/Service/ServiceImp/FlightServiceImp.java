package com.backend.flyquest.Service.ServiceImp;

import com.backend.flyquest.Model.Flight;
import com.backend.flyquest.Repository.FlightRepository;
import com.backend.flyquest.Service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceImp implements FlightService {
    @Autowired
    private FlightRepository flightRepository;
    @Override
    public void saveFlightToDatabase(Flight flight) {
        flightRepository.save(flight);
    }
}
