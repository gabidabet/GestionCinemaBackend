package com.example.cinemaback.entities;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;


@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class Seance {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime heureDebut;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime heureFin;
    @OneToMany(mappedBy = "seance")
    private Collection<Projection> projections;
}
