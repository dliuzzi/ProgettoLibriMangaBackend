package com.librimanga.backendlibrimanga.dto.outputs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UtentiDTO {
    private Integer id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
}
