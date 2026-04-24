package it.epicode.ProgettoFinale_S7_L5.payloads;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record EventoDTO(
        @NotBlank(message = "Il titolo è obbligatorio")
        String titolo,

        @NotBlank(message = "La descrizione è obbligatoria")
        String descrizione,

        @NotNull(message = "La data è obbligatoria")
        @Future(message = "La data dell'evento deve essere nel futuro, non abbiamo la macchina del tempo")
        LocalDate data,

        @NotBlank(message = "Il luogo è obbligatorio")
        String luogo,

        @Min(value = 1, message = "L'evento deve avere almeno 1 posto disponibile")
        int postiDisponibili
) {}
