package com.homepage.arsenal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Securityconfig{
    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final ArsenalUserDetailsService arsenalUserDetailsService;

    public Securityconfig(ArsenalUserDetailsService arsenalUserDetailsService) {
        this.arsenalUserDetailsService = arsenalUserDetailsService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/login", "/signup", "/error", "/css/**", "/js/**", "/images/**", "/static/**", "/*.ico").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                        .usernameParameter("email")
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
        http.cors();

        return http.build();
    }

}
