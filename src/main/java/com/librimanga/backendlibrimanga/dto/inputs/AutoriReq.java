package com.librimanga.backendlibrimanga.dto.inputs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AutoriReq {
    private Integer id;
    private String nome;
    private String cognome;
}
