package com.example.cinemaback.controllers;

import com.example.cinemaback.dao.CinemaRepository;
import com.example.cinemaback.dao.FilmRepository;
import com.example.cinemaback.dao.TicketRepository;
import com.example.cinemaback.entities.Cinema;
import com.example.cinemaback.entities.Film;
import com.example.cinemaback.entities.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController()
public class CinemaController {

    @Autowired
    FilmRepository filmRepository;
    @Autowired
    TicketRepository ticketRepository;

@GetMapping(path = "/images/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
public byte[] images(@PathVariable Long id) throws Exception{
    Film film = filmRepository.findById(id).get();
    String filmName = film.getImage();
    File file = new File(System.getProperty("user.home")+"\\Desktop\\cinema\\images\\"+filmName+".jpg");
    System.out.println(System.getProperty("user.home")+"\\Desktop\\cinema\\images\\"+filmName+".jpg");
    Path path = Paths.get(file.toURI());
    return Files.readAllBytes(path);
}

@CrossOrigin("*")
@PostMapping("/payerTickets")
@Transactional
public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm){
List<Ticket> listTickets = new ArrayList<>();
ticketForm.getTickets().forEach(idTicket->{
    System.out.println(idTicket);
    Ticket ticket = ticketRepository.findById(idTicket).get();
    ticket.setNomClient(ticketForm.getNomClient());
    ticket.setReserve(true);
    ticket.setCodePayement(ticketForm.getCodePayement());
    ticketRepository.save(ticket);
    listTickets.add(ticket);
});

return listTickets;
 }


}


@Data
class TicketForm{
    private String nomClient;
    private int codePayement;
    private List<Long> tickets = new ArrayList<>();


}