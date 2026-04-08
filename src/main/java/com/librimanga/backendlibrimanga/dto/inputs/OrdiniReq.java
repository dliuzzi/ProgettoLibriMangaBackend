package com.librimanga.backendlibrimanga.dto.inputs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
public class OrdiniReq {
    private Integer id;
    private Integer idUtente;
    private Set<DettagliOrdiniReq> dettagliOrdini;
}
