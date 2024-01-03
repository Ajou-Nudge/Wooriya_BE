package com.nudge.wooriya.config.security;

import com.nudge.wooriya.service.OAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;


@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final OAuth2UserService oAuth2UserService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .cors()
                .and()
                    .authorizeHttpRequests()
                    .requestMatchers("/imgupload").authenticated()
                    .requestMatchers("/comapnypost/post", "/comapnypost/update/{id}", "/comapnypost/delete/{id}").hasRole("COMPANY")
                    .requestMatchers("/grouppost/post",  "/grouppost/update/{id}", "/grouppost/delete/{id}").hasRole("GROUP")
                    .anyRequest().permitAll()
                .and()
                    .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return ((request, response, authentication) -> {
            DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) authentication.getPrincipal();

            String id = defaultOAuth2User.getAttributes().get("id").toString();
            String body = """
                    {"id":"%s"}
                    """.formatted(id);

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());

            PrintWriter writer = response.getWriter();
            writer.println(body);
            writer.flush();
        });
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}