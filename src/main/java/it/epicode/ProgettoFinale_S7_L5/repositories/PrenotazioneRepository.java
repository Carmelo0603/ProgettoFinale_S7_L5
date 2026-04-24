package it.epicode.ProgettoFinale_S7_L5.repositories;


import it.epicode.ProgettoFinale_S7_L5.entities.Evento;
import it.epicode.ProgettoFinale_S7_L5.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import it.epicode.ProgettoFinale_S7_L5.entities.Prenotazione;
import java.util.UUID;

@Repository
public interface PrenotazioneRepository   extends JpaRepository<Prenotazione, UUID> {

    boolean existsByUtenteAndEvento(Utente utente, Evento evento);
}
