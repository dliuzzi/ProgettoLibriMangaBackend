package com.librimanga.backendlibrimanga.dto.inputs;

import com.librimanga.backendlibrimanga.enums.StatoOrdine;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DettagliOrdiniReq {
    private Integer id;
    private Integer idLibro;
    private Integer quantita;
    private StatoOrdine stato;
}
