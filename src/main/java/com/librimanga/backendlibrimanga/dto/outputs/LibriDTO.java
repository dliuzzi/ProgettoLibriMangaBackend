package com.librimanga.backendlibrimanga.dto.outputs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
public class LibriDTO {
    private Integer id;
    private String titolo;
    private String descrizione;
    private String copertina;
    private Double prezzo;
    private LocalDate data_pubblicazione;

    private AutoriDTO autore;
    private Set<CategorieDTO> categorie;
    private CaseEditriciDTO casa_editrice;
}
