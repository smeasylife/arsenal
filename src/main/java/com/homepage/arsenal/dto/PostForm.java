package com.homepage.arsenal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostForm {
    @NotBlank
    private String title;

    @NotNull
    private String content;
}
