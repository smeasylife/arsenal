package com.homepage.arsenal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpForm {
    @NotBlank
    private String nickname;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;
}
