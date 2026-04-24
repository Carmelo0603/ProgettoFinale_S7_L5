package it.epicode.ProgettoFinale_S7_L5.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {

    public NotFoundException(UUID id) {
        super("Record con id " + id + " non trovato");

    }

    public NotFoundException(String message) {
        super(message);
    }
}
