package com.homepage.arsenal.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class Securityconfig{


    private final ArsenalUserDetailsService arsenalUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final SimpleUrlAuthenticationSuccessHandler authenticationSuccessHandler;

    public Securityconfig(ArsenalUserDetailsService arsenalUserDetailsService,PasswordEncoder passwordEncoder, SimpleUrlAuthenticationSuccessHandler authenticationSuccessHandler) {
        this.arsenalUserDetailsService = arsenalUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/login", "/signup", "/error", "/css/**", "/js/**", "/images/**", "/static/**", "/*.ico").permitAll()
                        .anyRequest().authenticated()
                )
                .cors(cors -> cors
                .configurationSource(CorsConfig.corsConfigurationSource()))
                .formLogin(form -> form
                        .loginPage("http://localhost:3000/login")
                        .loginProcessingUrl("/login")
                        .permitAll()
                        .usernameParameter("email")
                        .successHandler(authenticationSuccessHandler)
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/signin")
                        .invalidateHttpSession(true)
                )
                .sessionManagement(session -> session
                        .maximumSessions(2)
                        .expiredUrl("/signin")
                        .maxSessionsPreventsLogin(true)
                );


        return http.build();
    }



}
