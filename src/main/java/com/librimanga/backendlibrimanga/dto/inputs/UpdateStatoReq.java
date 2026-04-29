package com.librimanga.backendlibrimanga.dto.inputs;

import com.librimanga.backendlibrimanga.enums.StatoOrdine;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateStatoReq {
    private Integer id;
    private StatoOrdine status;
}
