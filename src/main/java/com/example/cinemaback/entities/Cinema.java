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
public class Cinema {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private Long x, y, z;
    @ManyToOne
    @JoinColumn
    private Ville ville;
    @OneToMany(mappedBy = "cinema")
    private Collection<Salle> salles;
}


