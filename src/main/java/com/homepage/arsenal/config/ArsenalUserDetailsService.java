package com.homepage.arsenal.config;

import com.homepage.arsenal.domain.Member;
import com.homepage.arsenal.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArsenalUserDetailsService implements UserDetailsService {
    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = null;
        try{
            member = memberService.getMemberByUsername(username);
        } catch (IllegalArgumentException e){
            throw new UsernameNotFoundException(e.getMessage());
        }
        return new DefaultUser(member);
    }
}
