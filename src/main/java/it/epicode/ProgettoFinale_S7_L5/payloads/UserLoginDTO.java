package it.epicode.ProgettoFinale_S7_L5.payloads;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// Struttura dati per la richiesta di login
public record UserLoginDTO(
        @NotBlank(message = "L'email è obbligatoria")
        @Email(message = "Formato email non valido")
        String email,

        @NotBlank(message = "La password è obbligatoria")
        String password
) {}
