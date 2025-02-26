package com.homepage.arsenal.service;

import com.homepage.arsenal.domain.Member;
import com.homepage.arsenal.dto.SignUpForm;
import com.homepage.arsenal.exception.MemberException;
import com.homepage.arsenal.repository.MemberRepository;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public Member getMemberByUsername(String username){
        return memberRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("Cannot find member by given username"));
    }

    @Transactional
    public Member saveMember(SignUpForm signUpForm){
        validateDuplicatedUsername(signUpForm.getUsername());
        return memberRepository.save(Member.builder()
                .username(signUpForm.getUsername())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .nickname(signUpForm.getNickname())
                .build());
    }

    private void validateDuplicatedUsername(String username){
        memberRepository.findByUsername(username)
                .ifPresent(m -> {
                    throw new MemberException("Already existion username");
                });
    }
}
