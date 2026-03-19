package com.librimanga.backendlibrimanga.dto.inputs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginReq {
    private String email;
    private String password;
}
