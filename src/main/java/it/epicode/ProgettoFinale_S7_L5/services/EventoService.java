package it.epicode.ProgettoFinale_S7_L5.services;

import it.epicode.ProgettoFinale_S7_L5.entities.Evento;
import it.epicode.ProgettoFinale_S7_L5.entities.Utente;
import it.epicode.ProgettoFinale_S7_L5.exceptions.NotFoundException;
import it.epicode.ProgettoFinale_S7_L5.exceptions.UnauthorizedException;
import it.epicode.ProgettoFinale_S7_L5.payloads.EventoDTO;
import it.epicode.ProgettoFinale_S7_L5.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    // Metodo per salvare l'evento associando automaticamente l'utente loggato come creatore
    public Evento save(EventoDTO dto, Utente creatore) {
        Evento e = new Evento();
        e.setTitolo(dto.titolo());
        e.setDescrizione(dto.descrizione());
        e.setData(dto.data());
        e.setLuogo(dto.luogo());
        e.setPostiDisponibili(dto.postiDisponibili());

        // Spero vivamente che tu abbia corretto quel "cratore" in Evento.java come ti avevo detto
        e.setCreatore(creatore);

        return eventoRepository.save(e);
    }

    public Evento findById(UUID id) {
        return eventoRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    // Metodo per eliminare un evento con controllo di proprietà
    public void delete(UUID eventoId, Utente utenteRichiedente) {
        Evento e = this.findById(eventoId);

        // Se l'ID del creatore non coincide con l'ID di chi fa la richiesta, scatta l'eccezione
        if(!e.getCreatore().getId().equals(utenteRichiedente.getId())) {
            throw new UnauthorizedException("Non hai i permessi per eliminare un evento che non hai creato tu.");
        }
        eventoRepository.delete(e);
    }

    // Metodo per aggiornare un evento, sempre con controllo di proprietà
    public Evento update(UUID eventoId, EventoDTO dto, Utente utenteRichiedente) {
        Evento e = this.findById(eventoId);

        if(!e.getCreatore().getId().equals(utenteRichiedente.getId())) {
            throw new UnauthorizedException("Non hai i permessi per modificare un evento che non hai creato tu.");
        }

        e.setTitolo(dto.titolo());
        e.setDescrizione(dto.descrizione());
        e.setData(dto.data());
        e.setLuogo(dto.luogo());
        e.setPostiDisponibili(dto.postiDisponibili());

        return eventoRepository.save(e);
    }
}
