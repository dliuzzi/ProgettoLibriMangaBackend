package com.librimanga.backendlibrimanga.dto.outputs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DettagliCarrelliDTO {
    private Integer id;
    private Integer quantita;
    private LibriDTO libro;
    private UtentiDTO utente;
}
