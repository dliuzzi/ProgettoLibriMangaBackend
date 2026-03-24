package com.librimanga.backendlibrimanga.dto.inputs;

import com.librimanga.backendlibrimanga.model.Autori;
import com.librimanga.backendlibrimanga.model.CaseEditrici;
import com.librimanga.backendlibrimanga.model.Categorie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
public class LibriReq {
    private Integer id;
    private String titolo;
    private String descrizione;
    private String copertina;
    private Double prezzo;
    private LocalDate dataPubblicazione;
    private Integer quantitaDisponibile;

    private Integer idAutore;
    private Set<Integer> idCategorie;
    private Integer idCasaEditrice;
}
