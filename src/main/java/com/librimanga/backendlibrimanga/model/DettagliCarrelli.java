package com.librimanga.backendlibrimanga.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="dettagli_carrelli")
public class DettagliCarrelli {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer quantita;

    @ManyToOne
    @JoinColumn(name = "id_libro", nullable = false)
    private Libri libro;

    @ManyToOne
    @JoinColumn(name = "id_utente", nullable = false)
    private Utenti utente;
}
