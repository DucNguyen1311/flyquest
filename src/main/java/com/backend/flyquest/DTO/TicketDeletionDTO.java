package com.backend.flyquest.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class TicketDeletionDTO {
    private java.sql.Timestamp departuretime;
    private int seattype;
    private String flightid;
}
