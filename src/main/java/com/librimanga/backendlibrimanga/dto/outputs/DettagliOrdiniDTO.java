package com.librimanga.backendlibrimanga.dto.outputs;

import com.librimanga.backendlibrimanga.enums.StatoOrdine;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class DettagliOrdiniDTO {
    private Integer id;
    private LibriDTO libro;
    private Integer quantita;
    private Double costoTotale;
    private String stato;
    private LocalDate data;
}
