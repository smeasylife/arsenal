package com.homepage.arsenal.controller.member;

import com.homepage.arsenal.dto.SignUpForm;
import com.homepage.arsenal.exception.MemberException;
import com.homepage.arsenal.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignUpController {
    private final MemberService memberService;

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("signUpForm", new SignUpForm());
        return "signup";
    }

    @PostMapping("signup")
    public String signup(@Validated @ModelAttribute SignUpForm signUpForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "signup";
        }

        memberService.saveMember(signUpForm);
        return "redirect:/login";
    }
}
