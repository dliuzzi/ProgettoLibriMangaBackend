package com.librimanga.backendlibrimanga.dto.outputs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
public class CaseEditriciDTO {
    private Integer id;
    private String nome;
    private String sede;
}
