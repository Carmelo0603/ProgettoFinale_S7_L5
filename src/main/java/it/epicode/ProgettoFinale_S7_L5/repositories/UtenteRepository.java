package it.epicode.ProgettoFinale_S7_L5.repositories;


import it.epicode.ProgettoFinale_S7_L5.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, UUID> {
    Optional<Utente> findByEmail(String email);
    Optional<Utente> findByUsername(String username);
}
