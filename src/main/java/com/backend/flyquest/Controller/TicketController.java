package com.backend.flyquest.Controller;

import com.backend.flyquest.Model.Ticket;
import com.backend.flyquest.Payload.TicketRequest;
import com.backend.flyquest.Repository.TicketRepository;
import com.backend.flyquest.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @PostMapping("/makereservation")
    public ResponseEntity<?> makeReservation(@RequestBody TicketRequest ticketRequest) {
        try {
            ticketService.makeReservation(new Ticket(ticketRequest));
            return ResponseEntity.ok(new Ticket(ticketRequest));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/getallticket/{id}")
    public ResponseEntity<?> getAllTicket(@PathVariable String id) {
        try {
            return ResponseEntity.ok(ticketService.getTicketsByAccountID(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
