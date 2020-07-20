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
    private Double longitude, latitude, altitude;
    @ManyToOne
    @JoinColumn
    private Ville ville;
    @OneToMany(mappedBy = "cinema")
    private Collection<Salle> salles;
	public Cinema(String nom, Double longitude, Double latitude, Double altitude, Ville ville,
			Collection<Salle> salles) {
		super();
		this.nom = nom;
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
		this.ville = ville;
		this.salles = salles;
	}
    
    
}


