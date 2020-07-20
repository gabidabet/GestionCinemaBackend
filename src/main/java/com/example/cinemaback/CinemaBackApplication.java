package com.example.cinemaback;

import com.example.cinemaback.dao.*;
import com.example.cinemaback.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.format.datetime.joda.LocalDateTimeParser;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
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
		Random random = new Random();
		salleRepository.findAll().forEach(salle -> {
			seanceRepository.findAll().forEach(seance -> {
				Projection projection = new Projection();
				int next = new Random().nextInt(6) + 1;
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

        
        // init villes
		Ville villeA = new Ville();
		villeA.setNom("Beni Mellal");
		villeA.setLongitude(Math.random() * 100);
		villeA.setLatitude(Math.random() * 100);
		villeA.setAltitude(Math.random() * 100);
		ArrayList<Cinema> villaACinema = new ArrayList<>();
		villaACinema.add(new Cinema("Cinema Atlas", Math.random() * 100, Math.random() * 100, Math.random() * 100, villeA, null));
		villaACinema.add(new Cinema("Cinema Youness", Math.random() * 100, Math.random() * 100, Math.random() * 100, villeA, null));
		villaACinema.add(new Cinema("Cinema Ain Asserdoun", Math.random() * 100, Math.random() * 100, Math.random() * 100, villeA, null));
		villaACinema.add(new Cinema("Cinema Takadoum", Math.random() * 100, Math.random() * 100, Math.random() * 100, villeA, null));
		villaACinema.add(new Cinema("Cinema Atlas 2", Math.random() * 100, Math.random() * 100, Math.random() * 100, villeA, null));
		villeA.setCinemas(villaACinema);
		villeRepository.save(villeA);
		cinemaRepository.saveAll(villaACinema);
		Ville villeB = new Ville();
		villeB.setNom("Casa Blanca");
		villeB.setLongitude(Math.random() * 100);
		villeB.setLatitude(Math.random() * 100);
		villeB.setAltitude(Math.random() * 100);
		ArrayList<Cinema> villaBCinema = new ArrayList<>();
		villaBCinema.add(new Cinema("Mega rama", Math.random() * 100, Math.random() * 100, Math.random() * 100, villeB, null));
		villaBCinema.add(new Cinema("Mega rama 2", Math.random() * 100, Math.random() * 100, Math.random() * 100, villeB, null));
		villaBCinema.add(new Cinema("Cinema Twin", Math.random() * 100, Math.random() * 100, Math.random() * 100, villeB, null));
		villaBCinema.add(new Cinema("Cinema Port", Math.random() * 100, Math.random() * 100, Math.random() * 100, villeB, null));
		villeB.setCinemas(villaBCinema);
		villeRepository.save(villeB);
		cinemaRepository.saveAll(villaBCinema);
		Ville villeC = new Ville();
		villeC.setNom("Rabat");
		villeC.setLongitude(Math.random() * 100);
		villeC.setLatitude(Math.random() * 100);
		villeC.setAltitude(Math.random() * 100);
		ArrayList<Cinema> villaCCinema = new ArrayList<>();
		villaCCinema.add(new Cinema("Megarama Arribat Center", Math.random() * 100, Math.random() * 100, Math.random() * 100, villeC, null));
		villaCCinema.add(new Cinema("Cinéma Paradise", Math.random() * 100, Math.random() * 100, Math.random() * 100, villeC, null));
		villaCCinema.add(new Cinema("ARMCDH", Math.random() * 100, Math.random() * 100, Math.random() * 100, villeC, null));
		villaCCinema.add(new Cinema("CINEATLAS", Math.random() * 100, Math.random() * 100, Math.random() * 100, villeC, null));
		villaCCinema.add(new Cinema("Supravision - Production Cinéma", Math.random() * 100, Math.random() * 100, Math.random() * 100, villeC, null));
		villeC.setCinemas(villaCCinema);
		villeRepository.save(villeC);
		cinemaRepository.saveAll(villaCCinema);
		Ville villeD = new Ville();
		villeD.setNom("Tanger");
		villeD.setLongitude(Math.random() * 100);
		villeD.setLatitude(Math.random() * 100);
		villeD.setAltitude(Math.random() * 100);
		ArrayList<Cinema> villaDCinema = new ArrayList<>();
		villaDCinema.add(new Cinema("Megarama Arribat Center", Math.random() * 100, Math.random() * 100, Math.random() * 100, villeD, null));
		villaDCinema.add(new Cinema("Cinéma Paradise", Math.random() * 100, Math.random() * 100, Math.random() * 100, villeD, null));
		villaDCinema.add(new Cinema("ARMCDH", Math.random() * 100, Math.random() * 100, Math.random() * 100, villeD, null));
		villaDCinema.add(new Cinema("CINEATLAS", Math.random() * 100, Math.random() * 100, Math.random() * 100, villeD, null));
		villaDCinema.add(new Cinema("Supravision - Production Cinéma", Math.random() * 100, Math.random() * 100, Math.random() * 100, villeD, null));
		villeD.setCinemas(villaDCinema);
		villeRepository.save(villeD);
		cinemaRepository.saveAll(villaDCinema);
		/*Ville villeE = new Ville();
		villeE.setNom("Beni Mellal");
		villeE.setLongitude(Math.random() * 100);
		villeE.setLatitude(Math.random() * 100);
		villeE.setAltitude(Math.random() * 100);
		villeRepository.save(villeE);
		Ville villeF = new Ville();
		villeF.setNom("Beni Mellal");
		villeF.setLongitude(Math.random() * 100);
		villeF.setLatitude(Math.random() * 100);
		villeF.setAltitude(Math.random() * 100);
		villeRepository.save(villeF);*/
		//init cinemas

	

		//init salles
		Random random = new Random();
		cinemaRepository.findAll().forEach(cinema -> {
			for(int i = 1; i <= random.nextInt(11); i++){
				Salle salle = new Salle();
				salle.setLongitude(Math.random() * 100);
				salle.setLatitude(Math.random() * 100);
				salle.setAltitude(Math.random() * 100);
				salle.setNom("salle"+i);
				salle.setCinema(cinema);
				salle.setNombrePlaces(10 + random.nextInt(11));
				salleRepository.save(salle);
			}
		});

		//init places
		salleRepository.findAll().forEach(salle -> {
			for(int i = 1; i <= salle.getNombrePlaces(); i++){
				Place place = new Place(i);
				place.setSalle(salle);
				placeRepository.save(place);
			}
		});


		//init seances
		for(int i = 1; i <= 5; i++){
			Seance seance = new Seance();
			seance.setHeureDebut(LocalTime.now().plusHours(i-2));
			seance.setHeureFin(LocalTime.now().plusHours(i));
			seanceRepository.save(seance);
		}

		//init categories

		categorieRepository.save(new Categorie("Fiction"));
		categorieRepository.save(new Categorie("Drama"));
		categorieRepository.save(new Categorie("Romance"));
		categorieRepository.save(new Categorie("Action"));
		categorieRepository.save(new Categorie("Adventure"));
		categorieRepository.save(new Categorie("Commedie"));
		categorieRepository.save(new Categorie("Sport"));
		//init films

		Film film1 = new Film();
		film1.setDuree(2);
		film1.setDescription("Two friends having bad day at 21 anniversary");
		film1.setNom("Las Vegas 21");
		film1.setImage("lv-21-img");
		film1.setCategorie(categorieRepository.findById((long)1 + random.nextInt(7)).get());
		filmRepository.save(film1);
		Film film2 = new Film();
		film2.setDuree(2);
		film2.setDescription("After joining jail he will try his best to run out");
		film2.setNom("Shawshank redemption");
		film2.setImage("sr-img");
		film2.setCategorie(categorieRepository.findById((long)1 + random.nextInt(7)).get());
		filmRepository.save(film2);
		Film film3 = new Film();
		film3.setDuree(2);
		film3.setDescription("A young boy, left without attention, delves into a life of petty crime.");
		film3.setNom("Les quatre cents coups");
		film3.setImage("lv-21-img");
		film3.setCategorie(categorieRepository.findById((long)1 + random.nextInt(7)).get());
		filmRepository.save(film3);
		Film film4 = new Film();
		film4.setDuree(2);
		film4.setDescription("24 hours in the lives of three young men in the French suburbs the day after a violent riot.");
		film4.setNom(" La haine ");
		film4.setImage("haine");
		film4.setCategorie(categorieRepository.findById((long)1 + random.nextInt(7)).get());
		filmRepository.save(film4);
		Film film5 = new Film();
		film5.setDuree(2);
		film5.setDescription("The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.");
		film5.setNom("Le parrain ");
		film5.setImage("godfather");
		film5.setCategorie(categorieRepository.findById((long)1 + random.nextInt(7)).get());
		filmRepository.save(film5);
		Film film6 = new Film();
		film6.setDuree(2);
		film6.setDescription("A film crew follows a ruthless thief and heartless killer as he goes about his daily routine. But complications set in when the film crew lose their objectivity and begin lending a hand.");
		film6.setNom("C'est arrivé près de chez vous ");
		film6.setImage("presvous");
		film6.setCategorie(categorieRepository.findById((long)1 + random.nextInt(7)).get());
		filmRepository.save(film6);
		/*Film film = new Film();
		film.setDuree(2);
		film.setDescription("Two friends having bad day at 21 anniversary");
		film.setNom("Las Vegas 21");
		film.setImage("lv-21-img");
		film.setCategorie(categorieRepository.findById((long)1 + random.nextInt(7)).get());
		filmRepository.save(film);
		Film film = new Film();
		film.setDuree(2);
		film.setDescription("Two friends having bad day at 21 anniversary");
		film.setNom("Las Vegas 21");
		film.setImage("lv-21-img");
		film.setCategorie(categorieRepository.findById((long)1 + random.nextInt(7)).get());
		filmRepository.save(film);
		Film film = new Film();
		film.setDuree(2);
		film.setDescription("Two friends having bad day at 21 anniversary");
		film.setNom("Las Vegas 21");
		film.setImage("lv-21-img");
		film.setCategorie(categorieRepository.findById((long)1 + random.nextInt(7)).get());
		filmRepository.save(film);
		Film film = new Film();
		film.setDuree(2);
		film.setDescription("Two friends having bad day at 21 anniversary");
		film.setNom("Las Vegas 21");
		film.setImage("lv-21-img");
		film.setCategorie(categorieRepository.findById((long)1 + random.nextInt(7)).get());
		filmRepository.save(film);
		Film film = new Film();
		film.setDuree(2);
		film.setDescription("Two friends having bad day at 21 anniversary");
		film.setNom("Las Vegas 21");
		film.setImage("lv-21-img");
		film.setCategorie(categorieRepository.findById((long)1 + random.nextInt(7)).get());
		filmRepository.save(film);
		Film film = new Film();
		film.setDuree(2);
		film.setDescription("Two friends having bad day at 21 anniversary");
		film.setNom("Las Vegas 21");
		film.setImage("lv-21-img");
		film.setCategorie(categorieRepository.findById((long)1 + random.nextInt(7)).get());
		filmRepository.save(film);
		Film film = new Film();
		film.setDuree(2);
		film.setDescription("Two friends having bad day at 21 anniversary");
		film.setNom("Las Vegas 21");
		film.setImage("lv-21-img");
		film.setCategorie(categorieRepository.findById((long)1 + random.nextInt(7)).get());
		filmRepository.save(film);
		Film film = new Film();
		film.setDuree(2);
		film.setDescription("Two friends having bad day at 21 anniversary");
		film.setNom("Las Vegas 21");
		film.setImage("lv-21-img");
		film.setCategorie(categorieRepository.findById((long)1 + random.nextInt(7)).get());
		filmRepository.save(film);
		Film film = new Film();
		film.setDuree(2);
		film.setDescription("Two friends having bad day at 21 anniversary");
		film.setNom("Las Vegas 21");
		film.setImage("lv-21-img");
		film.setCategorie(categorieRepository.findById((long)1 + random.nextInt(7)).get());
		filmRepository.save(film);
		Film film = new Film();
		film.setDuree(2);
		film.setDescription("Two friends having bad day at 21 anniversary");
		film.setNom("Las Vegas 21");
		film.setImage("lv-21-img");
		film.setCategorie(categorieRepository.findById((long)1 + random.nextInt(7)).get());
		filmRepository.save(film);
		Film film = new Film();
		film.setDuree(2);
		film.setDescription("Two friends having bad day at 21 anniversary");
		film.setNom("Las Vegas 21");
		film.setImage("lv-21-img");
		film.setCategorie(categorieRepository.findById((long)1 + random.nextInt(7)).get());
		filmRepository.save(film);
		Film film = new Film();
		film.setDuree(2);
		film.setDescription("Two friends having bad day at 21 anniversary");
		film.setNom("Las Vegas 21");
		film.setImage("lv-21-img");
		film.setCategorie(categorieRepository.findById((long)1 + random.nextInt(7)).get());
		filmRepository.save(film);*/


		//init projections
		initProjection();
		//initTicket();
		initTicket1();
	}
}
