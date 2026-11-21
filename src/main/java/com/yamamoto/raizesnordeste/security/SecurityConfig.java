package com.yamamoto.raizesnordeste.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(HttpMethod.POST, "/raizesnordeste/auth/login", "/raizesnordeste/usuarios").permitAll()
                    .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                    .requestMatchers(HttpMethod.POST, "/raizesnordeste/produtos/**", "/raizesnordeste/unidades/**", "/raizesnordeste/estoques/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT,"/raizesnordeste/produtos/**", "/raizesnordeste/unidades/**", "/raizesnordeste/estoques/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/raizesnordeste/produtos/**", "/raizesnordeste/unidades/**", "/raizesnordeste/estoques/**", "/raizesnordeste/usuarios/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/raizesnordeste/usuarios/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/raizesnordeste/pedidos/*/status").hasAnyRole("ADMIN", "COZINHEIRO", "ATENDENTE")
                    .requestMatchers(HttpMethod.GET,"/raizesnordeste/logs/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
            return http.build();
    }
}
