package com.example.jwt_demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/logout").permitAll()   // allow login/logout without token
                .anyRequest().authenticated()            // protect all other routes
            )
            // Disable Spring Security's default login form and HTTP Basic auth so our /login controller works as expected
            .formLogin(form -> form.disable())
            .httpBasic(httpBasic -> httpBasic.disable())
            // Disable Spring Security's built-in logout filter so our /logout controller can handle it
            .logout(logout -> logout.disable())
            .addFilterBefore(jwtFilter,
                    org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
