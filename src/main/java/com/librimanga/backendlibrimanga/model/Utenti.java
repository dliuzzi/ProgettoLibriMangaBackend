package com.librimanga.backendlibrimanga.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name="utenti")
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

    @Column(length = 30, nullable = false)
    private String password;
}
