package com.librimanga.backendlibrimanga.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="libri")
public class Libri {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String titolo;

    @Column(columnDefinition = "TEXT")
    private String descrizione;
    
    private String copertina;

    @Column(nullable = false)
    private Double prezzo;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate dataPubblicazione;

    @Column(nullable = false)
    private Integer quantitaDisponibile;

    @ManyToOne
    @JoinColumn(name = "id_autore")
    private Autori autore;

    @ManyToMany
    @JoinTable(
            name = "libri_categorie",
            joinColumns = @JoinColumn(name = "id_libro"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    private Set<Categorie> categorie;

    @ManyToOne
    @JoinColumn(name = "id_casa_editrice")
    private CaseEditrici casaEditrice;
}
