package com.homepage.arsenal.service;

import com.homepage.arsenal.domain.Member;
import com.homepage.arsenal.dto.SignUpRequestForm;
import com.homepage.arsenal.exception.MemberException;
import com.homepage.arsenal.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Member saveMember(SignUpRequestForm signUpRequestForm){
        validateDuplicatedUsername(signUpRequestForm.getEmail());
        return memberRepository.save(Member.builder()
                .email(signUpRequestForm.getEmail())
                .password(passwordEncoder.encode(signUpRequestForm.getPassword()))
                .nickname(signUpRequestForm.getNickname())
                .build());
    }

    private void validateDuplicatedUsername(String username){
        memberRepository.findByUsername(username)
                .ifPresent(m -> {
                    throw new MemberException("Already existion username");
                });
    }
}
