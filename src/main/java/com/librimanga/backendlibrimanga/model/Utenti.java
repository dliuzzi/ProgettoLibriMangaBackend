package com.librimanga.backendlibrimanga.model;

import com.librimanga.backendlibrimanga.enums.Ruoli;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="utenti")
public class Utenti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30, nullable = false)
    private String nome;

    @Column(length = 30, nullable = false)
    private String cognome;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Ruoli ruolo;

    @Column(length = 100)
    private String via;

    @Column(length = 100)
    private String provincia;

    @Column(length = 100)
    private String telefono;

    private String immagineProfilo;

    @ManyToMany
    @JoinTable(
            name = "libri_preferiti",
            joinColumns = @JoinColumn(name = "id_utente"),
            inverseJoinColumns = @JoinColumn(name = "id_libro"))
    Set<Libri> libriPreferiti;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ordini> ordini;
}