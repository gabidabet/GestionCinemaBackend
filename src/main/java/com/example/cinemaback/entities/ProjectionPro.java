package com.example.cinemaback.entities;

import com.example.cinemaback.entities.Film;
import com.example.cinemaback.entities.Salle;
import com.example.cinemaback.entities.Seance;
import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;

@Projection(name = "pro1", types = {com.example.cinemaback.entities.Projection.class})
public interface ProjectionPro {

    public Long getId();
    public Integer getPrix();
    public Salle getSalle();
    public Film getFilm();
    public Seance getSeance();
    public Collection<Ticket> getTickets();


}


