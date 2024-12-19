package com.backend.flyquest.Repository.RepositoryImp;

import com.backend.flyquest.Model.Ticket;
import com.backend.flyquest.Repository.CustomRepository.TicketCustomRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TicketCustomRepositoryImp implements TicketCustomRepository {

    @Autowired
    private EntityManager em;

    @Transactional
    @Override
    public void makeReservationAndUpdateSeat(Ticket ticket) {
        String seatType = "availableeconomyseat";

        switch (ticket.getSeatType()) {
            case 1 -> seatType = "availablebusinessseat";
            case 2 -> seatType = "availablefirstclassseat";
        }

        System.out.println(STR."\{seatType} \{ticket.toString()}");
        String sql = "(SELECT " + seatType + " FROM flight WHERE flightid = :flightID)";
        if ((Integer) em.createNativeQuery(sql, Integer.class).setParameter("flightID", ticket.getFlightId()).getSingleResult() > 0) {
            String updateSql = "UPDATE flight\n" +
                    "    SET " + seatType + " = " + seatType + " - 1\n" +
                    "    WHERE flightid = :FlightIDToUpdate; \n" +
                    "INSERT INTO tickethistory (flightid, accountid, datebooked, ticketstate, seattype, ticketid)\n" +
                    " VALUES ( :FlightIDToInsert, :AccountID, :DateBooked, :TicketState, :SeatType, :TicketID);";
            em.createNativeQuery(updateSql)
                    .setParameter("FlightIDToUpdate", ticket.getFlightId())
                    .setParameter("FlightIDToInsert", ticket.getFlightId())
                    .setParameter("AccountID", ticket.getAccountId())
                    .setParameter("DateBooked", ticket.getDateBooked())
                    .setParameter("TicketState", ticket.getState())
                    .setParameter("SeatType", ticket.getSeatType())
                    .setParameter("TicketID", ticket.getTicketId())
                    .executeUpdate();
        }
        else {
            throw new RuntimeException("No more seats available");
        }
    }
}
