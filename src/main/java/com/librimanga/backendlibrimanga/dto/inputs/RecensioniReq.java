package com.librimanga.backendlibrimanga.dto.inputs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecensioniReq {
    private Integer id;
    private Integer stelle;
    private String contenuto;

    private Integer idLibro;
    private Integer idUtente;
}
