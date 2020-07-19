package com.example.cinemaback.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class Film {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private double duree;
    private String image;
    @ManyToOne
    @JoinColumn
    private Categorie categorie;
    @OneToMany(mappedBy = "film")
    private Collection<Projection> projections;

}
