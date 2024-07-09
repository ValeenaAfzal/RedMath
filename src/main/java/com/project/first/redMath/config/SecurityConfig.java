package com.project.first.redMath.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableAsync
@EnableScheduling
//@EnableConfigurationProperties(value = { })
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    @Value("${security.ignored}")
    private String[] ignored;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> {
            for (String location : ignored) {
                web.ignoring().requestMatchers(AntPathRequestMatcher.antMatcher(location));
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.formLogin(Customizer.withDefaults());
//        http.authorizeHttpRequests(config -> config.anyRequest().authenticated());
//        http.csrf(csrf -> csrf.disable());
//        return http.build();
        http.formLogin(Customizer.withDefaults());
        http.authorizeHttpRequests(config -> config
                .anyRequest().authenticated());
        http.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .sessionAuthenticationStrategy(new NullAuthenticatedSessionStrategy()));
        return http.build();
    }
}