package com.homepage.arsenal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class Securityconfig{


    private final ArsenalUserDetailsService arsenalUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final SimpleUrlAuthenticationSuccessHandler authenticationSuccessHandler;
    private final LogoutSuccessHandler logoutSuccessHandler;

    public Securityconfig(ArsenalUserDetailsService arsenalUserDetailsService,
                          PasswordEncoder passwordEncoder,
                          SimpleUrlAuthenticationSuccessHandler authenticationSuccessHandler,
                          LogoutSuccessHandler logoutSuccessHandler
            ) {
        this.arsenalUserDetailsService = arsenalUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.logoutSuccessHandler = logoutSuccessHandler;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/login", "/signup", "/error", "/css/**", "/js/**", "/images/**", "/static/**", "/*.ico", "/session").permitAll()
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
                        .invalidateHttpSession(true)
                        .logoutSuccessHandler(logoutSuccessHandler)
                        .deleteCookies("JSESSIONID")
                )
                .sessionManagement(session -> session
                        .maximumSessions(2)
                        .maxSessionsPreventsLogin(true)
                );


        return http.build();
    }



}
