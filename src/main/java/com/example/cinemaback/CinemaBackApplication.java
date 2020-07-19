package com.example.cinemaback;

import com.example.cinemaback.dao.*;
import com.example.cinemaback.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;


@SpringBootApplication
public class CinemaBackApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(CinemaBackApplication.class, args);
	}

	@Autowired
	CinemaRepository cinemaRepository;
	@Autowired
	VilleRepository villeRepository;
	@Autowired
	SalleRepository salleRepository;
	@Autowired
	FilmRepository filmRepository;
	@Autowired
	CategorieRepository categorieRepository;
	@Autowired
	ProjectionRepository projectionRepository;
	@Autowired
	SeanceRepository seanceRepository;
	@Autowired
	TicketRepository ticketRepository;
	@Autowired
	PlaceRepository placeRepository;
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;

	void initProjection(){
		salleRepository.findAll().forEach(salle -> {
			seanceRepository.findAll().forEach(seance -> {
				Projection projection = new Projection();
				int next = new Random().nextInt(5) + 1;
				Film film = filmRepository.findById((long)next).get();
				projection.setSalle(salle);
				projection.setFilm(film);
				projection.setSeance(seance);
				projectionRepository.save(projection);
			});
		});
	}



	@Transactional
	void initTicket(){
		projectionRepository.findAll().forEach(projection -> {
			for (Place place : projection.getSalle().getPlaces()) {
				Ticket ticket = new Ticket();
				ticket.setProjection(projection);
				ticket.setPlace(place);
				ticket.setNomClient("salah eddine");
				ticket.setCodePayement(12345);
				ticketRepository.save(ticket);

			}
		});
	}

	void initTicket1(){

		projectionRepository.findAll().forEach(projection -> {
			for(int i = 1; i <= 20; i++){
				Ticket ticket = new Ticket();
				ticket.setCodePayement(i);
				ticket.setNomClient("salah eddine");
				ticket.setProjection(projection);
				ticket.setNumero(i);
				ticketRepository.save(ticket);

			}
		});

	}


	@Override
	public void run(String... args) throws Exception {

        repositoryRestConfiguration.exposeIdsFor(Film.class, Salle.class, Ticket.class);

		for(int i = 1; i <= 10; i++){
			Ville ville = new Ville();
			ville.setNom("ville"+i);
			villeRepository.save(ville);

		}

		//init cinemas

		villeRepository.findAll().forEach(ville -> {
			for(int i = 1; i <= 10; i++){
				Cinema cinema = new Cinema();
				cinema.setNom("cinema"+i);
				cinema.setVille(ville);
				cinemaRepository.save(cinema);
			}

		});

		//init salles

		cinemaRepository.findAll().forEach(cinema -> {
			for(int i = 1; i <= 10; i++){
				Salle salle = new Salle();
				salle.setNom("salle"+i);
				salle.setCinema(cinema);
				salle.setNombrePlaces(20+i);
				salleRepository.save(salle);
			}
		});

		//init places
		salleRepository.findAll().forEach(salle -> {
			for(int i = 1; i <= salle.getNombrePlaces(); i++){
				Place place = new Place();
				place.setSalle(salle);
				placeRepository.save(place);
			}
		});


		//init seances
		for(int i = 1; i <= 5; i++){
			Seance seance = new Seance();
			seance.setHeureDebut(LocalTime.now().plusHours(i-1));
			seance.setHeureFin(LocalTime.now().plusHours(i));
			seanceRepository.save(seance);
		}

		//init categories

		for(int i = 1; i <= 5; i++){
			Categorie categorie = new Categorie();
			categorie.setNom("categorie"+i);
			categorieRepository.save(categorie);

		}

		//init films

		for(int i = 1; i <= 5; i++){
			Film film = new Film();
			film.setNom("film"+i);
			film.setImage("film"+i);
			film.setCategorie(categorieRepository.findById((long)1).get());
			filmRepository.save(film);
		}


		//init projections
		initProjection();
		//initTicket();
		initTicket1();



/*

         //init tickets

*/





	}
}
