package com.librimanga.backendlibrimanga.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name="recensioni")
public class Recensioni {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer stelle;
    private String contenuto;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "id_libro")
    private Libri libro;

    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utenti utente;
}
