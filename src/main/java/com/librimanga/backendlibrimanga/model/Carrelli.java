package com.librimanga.backendlibrimanga.model;

import jakarta.persistence.*;

import java.time.LocalDate;

public class Carrelli {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_utente", nullable = false)
    private Utenti utente;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate dataCreazione;
}
