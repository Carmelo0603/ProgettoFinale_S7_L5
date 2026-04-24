package it.epicode.ProgettoFinale_S7_L5.services;

import it.epicode.ProgettoFinale_S7_L5.entities.Prenotazione;
import it.epicode.ProgettoFinale_S7_L5.entities.Utente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    // Se avessi un account Mailgun reale, metteresti qui le chiavi lette da application.properties
    @Value("${mailgun.apikey:nessuna_chiave}")
    private String apiKey;

    public void sendConfermaPrenotazione(Utente utente, Prenotazione prenotazione) {
        String subject = "Conferma Prenotazione: " + prenotazione.getEvento().getTitolo();
        String body = "Ciao " + utente.getUsername() + ",\n\nLa tua prenotazione per l'evento '"
                + prenotazione.getEvento().getTitolo() + "' del "
                + prenotazione.getEvento().getData() + " presso "
                + prenotazione.getEvento().getLuogo() + " è confermata!\n\nTi aspettiamo!";

        // Simulazione dell'invio per evitare crash a runtime durante l'esame
        System.out.println("=======================================");
        System.out.println("SIMULAZIONE INVIO EMAIL (MAILGUN)");
        System.out.println("A: " + utente.getEmail());
        System.out.println("Oggetto: " + subject);
        System.out.println("Testo: \n" + body);
        System.out.println("=======================================");
    }
}