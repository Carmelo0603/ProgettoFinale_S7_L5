package it.epicode.ProgettoFinale_S7_L5.controllers;

import it.epicode.ProgettoFinale_S7_L5.entities.Evento;
import it.epicode.ProgettoFinale_S7_L5.entities.Utente;
import it.epicode.ProgettoFinale_S7_L5.payloads.EventoDTO;
import it.epicode.ProgettoFinale_S7_L5.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/eventi")
public class EventiController {

    @Autowired
    private EventoService eventoService;

    // Chiunque (purché autenticato con token) può vedere la lista degli eventi
    @GetMapping
    public List<Evento> getAllEventi() {
        return eventoService.findAll();
    }

    // Solo un ORGANIZZATORE può creare l'evento. Spring passa in automatico l'utente loggato.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_ORGANIZZAZIONE')")
    public Evento createEvento(@RequestBody @Validated EventoDTO dto, @AuthenticationPrincipal Utente currentUser) {
        return eventoService.save(dto, currentUser);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ORGANIZZAZIONE')")
    public Evento updateEvento(@PathVariable UUID id, @RequestBody @Validated EventoDTO dto, @AuthenticationPrincipal Utente currentUser) {
        return eventoService.update(id, dto, currentUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_ORGANIZZAZIONE')")
    public void deleteEvento(@PathVariable UUID id, @AuthenticationPrincipal Utente currentUser) {
        eventoService.delete(id, currentUser);
    }
}