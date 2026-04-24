package it.epicode.ProgettoFinale_S7_L5.repositories;

import it.epicode.ProgettoFinale_S7_L5.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventoRepository extends JpaRepository<Evento, UUID> {

}
