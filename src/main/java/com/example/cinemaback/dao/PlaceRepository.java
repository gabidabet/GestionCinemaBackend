package com.example.cinemaback.dao;

import com.example.cinemaback.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin("*")
public interface PlaceRepository extends JpaRepository<Place, Long> {
}
