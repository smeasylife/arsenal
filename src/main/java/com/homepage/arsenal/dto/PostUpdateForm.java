package com.homepage.arsenal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostUpdateForm {
    @NotBlank
    private Long id;

    @NotBlank
    private String title;

    @NotNull
    private String content;
}
