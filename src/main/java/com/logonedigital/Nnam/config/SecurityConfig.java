package com.logonedigital.Nnam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Désactiver la protection CSRF pour les APIs stateless
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable())) // Désactiver les restrictions d'affichage en iframe pour Swagger
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // API stateless
                .authorizeHttpRequests(auth -> auth
                        // Autoriser l'accès à Swagger et aux ressources associées
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**"
                        ).permitAll() // Swagger accessible sans authentification

                        // Autoriser l'accès à certaines routes publiques comme l'inscription ou la récupération du mot de passe
                        .requestMatchers("/public/**", "/register", "/forgot-password").permitAll()

                        // Requérir l'authentification uniquement pour les actions sensibles (ajout, mise à jour, suppression d'utilisateur)
                        .requestMatchers("/api/utilisateurs/add", "/api/utilisateurs/update/**", "/api/utilisateurs/delete/**").authenticated()

                        // Permettre les autres requêtes sans authentification
                        .anyRequest().permitAll()
                )
                .httpBasic(withDefaults())  // Utiliser l'authentification basique uniquement pour les routes sécurisées
                .build();
    }
}
