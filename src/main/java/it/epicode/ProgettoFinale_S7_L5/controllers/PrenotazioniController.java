package it.epicode.ProgettoFinale_S7_L5.controllers;

import it.epicode.ProgettoFinale_S7_L5.entities.Prenotazione;
import it.epicode.ProgettoFinale_S7_L5.entities.Utente;
import it.epicode.ProgettoFinale_S7_L5.payloads.PrenotazioneDTO;
import it.epicode.ProgettoFinale_S7_L5.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioniController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    // Ritorna solo le prenotazioni dell'utente che sta facendo la richiesta
    @GetMapping("/mie")
    public List<Prenotazione> getMiePrenotazioni(@AuthenticationPrincipal Utente currentUser) {
        return prenotazioneService.getPrenotazioniUtente(currentUser);
    }

    // Effettua una nuova prenotazione
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione createPrenotazione(@RequestBody @Validated PrenotazioneDTO dto, @AuthenticationPrincipal Utente currentUser) {
        return prenotazioneService.save(dto, currentUser);
    }
}