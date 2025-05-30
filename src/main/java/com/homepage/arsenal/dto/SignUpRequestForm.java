package com.homepage.arsenal.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestForm {
    @NotBlank
    private String nickname;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
