package it.epicode.ProgettoFinale_S7_L5.controllers;

import it.epicode.ProgettoFinale_S7_L5.entities.Utente;
import it.epicode.ProgettoFinale_S7_L5.payloads.LoginResponseDTO;
import it.epicode.ProgettoFinale_S7_L5.payloads.UserLoginDTO;
import it.epicode.ProgettoFinale_S7_L5.payloads.UserRegisterDTO;
import it.epicode.ProgettoFinale_S7_L5.services.AuthService;
import it.epicode.ProgettoFinale_S7_L5.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UtenteService utenteService;

    // Endpoint di login per ottenere il Bearer Token
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Validated UserLoginDTO loginDTO) {
        String token = authService.authenticateUser(loginDTO);
        return new LoginResponseDTO(token);
    }

    // Endpoint di registrazione pubblica
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente register(@RequestBody @Validated UserRegisterDTO registerDTO) {
        return utenteService.save(registerDTO);
    }
}
