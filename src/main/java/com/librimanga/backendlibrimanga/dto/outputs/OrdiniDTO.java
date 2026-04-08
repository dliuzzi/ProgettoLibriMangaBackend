package com.librimanga.backendlibrimanga.dto.outputs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrdiniDTO {
    private Integer id;
    private UtentiDTO utente;
    private List<DettagliOrdiniDTO> dettagliOrdine;
}
