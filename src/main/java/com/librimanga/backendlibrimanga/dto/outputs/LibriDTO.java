package com.librimanga.backendlibrimanga.dto.outputs;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
public class LibriDTO {
    private Integer id;
    private String titolo;
    private String descrizione;
    private String copertina;
    private Double prezzo;
    private LocalDate dataPubblicazione;
    private Integer quantitaDisponibile;

    private AutoriDTO autore;
    private List<CategorieDTO> categorie;
    private CaseEditriciDTO casaEditrice;
}
