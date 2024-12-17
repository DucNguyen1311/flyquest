package com.backend.flyquest.Repository.RepositoryImp;

import com.backend.flyquest.DTO.FlightDTO;
import com.backend.flyquest.Model.Flight;
import com.backend.flyquest.Repository.FlightCustomRepository;
import jakarta.persistence.Access;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FlightCustomRepositoryImp implements FlightCustomRepository {
    @Autowired
    private EntityManager em;
    @Override
    public List<FlightDTO> findFlightByDepartureAndDestination(String departure, String destination){
        String sql = "SELECT f.flightid, f.flightnumber, f.availableeconomyseat, f.availablebusinessseat, f.availablefirstclassseat, f.price, f.departuretime, f.expectedarrival,\n" +
                "\ta.name as airlinename, a.airlinecode,\n" +
                "\tap1.location AS departurelocation, ap1.name AS departureairportname, ap1.airportcode AS departureairportcode,\n" +
                "\tap2.location AS destinationlocation, ap2.name AS destinationairportname, ap2.airportcode AS destinationairportcode\n" +
                "\tFROM flight f\n" +
                "\tJOIN airline a ON f.airlineid = a.airlineid\n" +
                "\tINNER JOIN airport ap1 ON f.departureid = ap1.airportid\n" +
                "\tINNER JOIN airport ap2 ON f.destinationid = ap2.airportid\n" +
                "\tWHERE f.departureid = :departure AND f.destinationid=:destination;";
        System.out.println(departure + " " + destination);
        return em.createNativeQuery(sql).setParameter("departure", departure).setParameter("destination", destination).getResultList();
    }
}
