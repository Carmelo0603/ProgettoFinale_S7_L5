package it.epicode.ProgettoFinale_S7_L5.payloads;

import java.time.LocalDateTime;
//Immutabile, serve per incapsulare la risposta di errore da inviare al client
public record ErrorsDTO(String message, LocalDateTime timestamp) {
}
