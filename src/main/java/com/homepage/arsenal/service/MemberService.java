package com.homepage.arsenal.service;

import com.homepage.arsenal.domain.Member;
import com.homepage.arsenal.dto.SignUpRequestForm;
import com.homepage.arsenal.enums.RoleType;
import com.homepage.arsenal.exception.MemberException;
import com.homepage.arsenal.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member getMemberByEmail(String email){
        return memberRepository.findByEmail(email).orElseThrow(() -> new MemberException("Cannot find member by given email"));
    }

    @Transactional
    public Member saveMember(SignUpRequestForm signUpRequestForm){
        validateDuplicatedUsername(signUpRequestForm.getEmail());
        return memberRepository.save(Member.builder()
                .email(signUpRequestForm.getEmail())
                .password(passwordEncoder.encode(signUpRequestForm.getPassword()))
                .nickname(signUpRequestForm.getNickname())
                .role(RoleType.ROLE_USER)
                .build());
    }

    private void validateDuplicatedUsername(String email){
        memberRepository.findByEmail(email)
                .ifPresent(m -> {
                    throw new MemberException("Already existion username");
                });
    }
}
