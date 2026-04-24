package it.epicode.ProgettoFinale_S7_L5.services;

import it.epicode.ProgettoFinale_S7_L5.entities.Utente;
import it.epicode.ProgettoFinale_S7_L5.exceptions.UnauthorizedException;
import it.epicode.ProgettoFinale_S7_L5.payloads.UserLoginDTO;
import it.epicode.ProgettoFinale_S7_L5.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UtenteService utenteService;

    // Qui hai chiamato l'istanza passwordEncoder, quindi useremo questo nome
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUser(UserLoginDTO dto) {
        Utente u = utenteService.findByEmail(dto.email());

        // Utilizzo corretto dell'istanza iniettata per confrontare la password in chiaro con l'hash
        if (passwordEncoder.matches(dto.password(), u.getPassword())) {
            return jwtTools.createToken(u);
        } else {
            throw new UnauthorizedException("Credenziali non valide.");
        }
    }
}