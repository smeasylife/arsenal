package com.homepage.arsenal.controller.member;

import com.homepage.arsenal.dto.SignUpRequestForm;
import com.homepage.arsenal.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignUpController {
    private final MemberService memberService;

    @PostMapping("signup")
    public String signup(@Valid @ModelAttribute SignUpRequestForm signUpRequestForm) {
        memberService.saveMember(signUpRequestForm);
        return "redirect:/login";
    }
}
