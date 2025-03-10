package com.homepage.arsenal.controller.member;

import com.homepage.arsenal.dto.SignUpRequestForm;
import com.homepage.arsenal.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class SignUpController {
    private final MemberService memberService;

    @PostMapping("signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignUpRequestForm signUpRequestForm) {
        memberService.saveMember(signUpRequestForm);
        return ResponseEntity.ok("회원가입 성공");
    }
}
