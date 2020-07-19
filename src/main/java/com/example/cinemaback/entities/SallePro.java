package com.example.cinemaback.entities;


import org.springframework.data.rest.core.config.Projection;
import java.util.Collection;


@Projection(name = "pro2", types = {Salle.class})
public interface SallePro {
    public Collection<com.example.cinemaback.entities.Projection> getProjections();
}
