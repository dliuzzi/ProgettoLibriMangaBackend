package com.librimanga.backendlibrimanga.dto.outputs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class RecensioniDTO {
    private Integer id;
    private Integer stelle;
    private String contenuto;
    private LocalDate data;

    private LibriDTO libro;
    private UtentiDTO utente;
}
