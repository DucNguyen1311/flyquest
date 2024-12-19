package com.backend.flyquest.Service;

import com.backend.flyquest.Model.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {
    public void makeReservation(Ticket ticket);
    public List<Ticket> getTicketsByAccountID(String id);
}
