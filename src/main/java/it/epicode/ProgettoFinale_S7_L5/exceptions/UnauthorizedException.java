package it.epicode.ProgettoFinale_S7_L5.exceptions;

// Eccezione custom per gestire i fallimenti di autenticazione e token non validi
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
