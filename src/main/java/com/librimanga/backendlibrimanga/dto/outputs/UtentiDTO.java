package com.librimanga.backendlibrimanga.dto.outputs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UtentiDTO {
    private Integer id;
    private String nome;
    private String cognome;
    private String email;
    private String ruolo;
    private String via;
    private String provincia;
    private String telefono;
    private List<LibriDTO> libriPreferiti;
}
