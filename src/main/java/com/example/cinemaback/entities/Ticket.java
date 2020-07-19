package com.example.cinemaback.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class Ticket {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean reserve = false;
    private int numero;
    private String nomClient;
    @Column(unique = false)
    private int codePayement;
    @ManyToOne
    @JoinColumn
    private Place place;
    @ManyToOne
    @JoinColumn
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Projection projection;
}
