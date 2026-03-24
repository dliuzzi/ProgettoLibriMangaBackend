package com.librimanga.backendlibrimanga.model;

import com.librimanga.backendlibrimanga.enums.StatoOrdine;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name="dettagli_ordini")
public class DettagliOrdini {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer quantita;

    @Column(nullable = false)
    private Integer costoTotale;

    @Column(nullable = false)
    private StatoOrdine stato;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "id_libro")
    private Libri libro;

    @ManyToOne
    @JoinColumn(name = "id_ordine")
    private Libri ordine;
}
