package com.productservice.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpDTO {
    private String email;
    private String password;
    private String name;
}
