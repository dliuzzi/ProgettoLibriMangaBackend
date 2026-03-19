package com.librimanga.backendlibrimanga.dto.outputs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginDTO {
    private Integer id;
    private String email;
}
