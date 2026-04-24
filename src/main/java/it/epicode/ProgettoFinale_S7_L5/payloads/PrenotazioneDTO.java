package it.epicode.ProgettoFinale_S7_L5.payloads;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record PrenotazioneDTO(
        @NotNull(message = "L'ID dell'evento è obbligatorio")
        UUID eventoId
) {}