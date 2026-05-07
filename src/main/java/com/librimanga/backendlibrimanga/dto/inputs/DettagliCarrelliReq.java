package com.librimanga.backendlibrimanga.dto.inputs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DettagliCarrelliReq {
    private Integer id;
    private Integer quantita;
    private Integer idLibro;
    private Integer idUtente;
}
