package com.homepage.arsenal.dto;

import com.homepage.arsenal.domain.Member;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class CommentForm {
    @NotNull
    private Member writer;

    @NotNull
    @Max(300)
    private String content;

    @NotBlank
    private LocalDateTime date;
}
