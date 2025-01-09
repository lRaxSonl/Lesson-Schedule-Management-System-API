package com.scheduleManagementSystem.scheduleManagementSystem.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtFilter jwtFilter;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                                .anyRequest().permitAll()
                        /*
                        .requestMatchers("/api/auth/**", "/api/teachers/create", "/api/students/create").permitAll()

                        .requestMatchers("/api/teachers/**", "/api/schedule/create/**", "/api/schedule/update/**",
                                "/api/schedule/delete/**", "/api/groups/delete/**", "/api/groups/update/**").hasRole("TEACHER")

                        .requestMatchers("/api/teachers/**", "/api/schedule/create/", "/api/schedule/update/",
                                "/api/schedule/delete/**", "/api/groups/delete/**", "/api/groups/update/",
                                "/api/lesson/**", "/api/lesson").hasRole("TEACHER")

                        .requestMatchers("/api/students/**", "/api/students").hasAnyRole("STUDENT")

                        .requestMatchers("/api/schedule/**", "/api/groups/**", "/api/schedule", "/api/groups")
                        .hasAnyRole("STUDENT", "TEACHER")

                        .requestMatchers("/api/auth/**", "/api/teachers/create", "/api/students/create").permitAll()
                        .anyRequest().authenticated()
                         */
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
