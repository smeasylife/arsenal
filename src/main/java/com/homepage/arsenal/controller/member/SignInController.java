package com.homepage.arsenal.controller.member;


import com.homepage.arsenal.dto.SignInForm;
import com.homepage.arsenal.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignInController {
    @GetMapping("/login")
    public String signIn(Model model) {
        model.addAttribute("signInForm", new SignInForm());
        return "login";
    }

}
