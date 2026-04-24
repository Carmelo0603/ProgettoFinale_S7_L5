package it.epicode.ProgettoFinale_S7_L5.services;

import it.epicode.ProgettoFinale_S7_L5.entities.Evento;
import it.epicode.ProgettoFinale_S7_L5.entities.Prenotazione;
import it.epicode.ProgettoFinale_S7_L5.entities.Utente;
import it.epicode.ProgettoFinale_S7_L5.exceptions.BadRequestException;
import it.epicode.ProgettoFinale_S7_L5.payloads.PrenotazioneDTO;
import it.epicode.ProgettoFinale_S7_L5.repositories.EventoRepository;
import it.epicode.ProgettoFinale_S7_L5.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private EmailService emailService;

    public Prenotazione save(PrenotazioneDTO dto, Utente currentUser) {

        Evento evento = eventoService.findById(dto.eventoId());


        if (evento.getPostiDisponibili() <= 0) {
            throw new BadRequestException("Spiacenti, l'evento è sold out.");
        }
        if (prenotazioneRepository.existsByUtenteAndEvento(currentUser, evento)) {
            throw new BadRequestException("Hai già prenotato un posto per questo evento.");
        }


        evento.setPostiDisponibili(evento.getPostiDisponibili() - 1);
        eventoRepository.save(evento);


        Prenotazione p = new Prenotazione();
        p.setUtente(currentUser);
        p.setEvento(evento);

        Prenotazione salvata = prenotazioneRepository.save(p);


        emailService.sendConfermaPrenotazione(currentUser, salvata);


        return salvata;
    }

    public List<Prenotazione> getPrenotazioniUtente(Utente currentUser) {
        return prenotazioneRepository.findByUtente(currentUser);
    }
}