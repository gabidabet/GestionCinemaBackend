package com.example.cinemaback.entities;


import org.springframework.data.rest.core.config.Projection;

import java.time.LocalTime;
import java.util.Collection;

@Projection(name = "pro3", types = Seance.class)
public interface SeancePro {
    public LocalTime getHeureDebut();
    public LocalTime getHeureFin();
    public Collection<Ticket> getTickets();

}
