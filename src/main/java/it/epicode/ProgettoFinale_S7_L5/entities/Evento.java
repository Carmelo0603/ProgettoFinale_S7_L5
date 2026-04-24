package it.epicode.ProgettoFinale_S7_L5.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "eventi")
@Getter
@Setter
@NoArgsConstructor

public class Evento {

@Id
@GeneratedValue
@Setter(AccessLevel.NONE)
private UUID id;

@Column(nullable = false)
    private String titolo;
private String descrizione;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private String luogo;

    @Column(nullable = false)
    private int postiDisponibili;

    @ManyToOne
    @JoinColumn(name= "creatore_id", nullable = false)
    private Utente creatore;

}
