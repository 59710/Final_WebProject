package com.example.Final_WebProject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ProjectSecurity {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())

                .formLogin(formLogin -> formLogin
                        .loginPage("/login-page")
                        .failureUrl("//login-page")
                        .defaultSuccessUrl("/home")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .authorizeHttpRequests( auth -> auth
//                        .requestMatchers("/css/**", "/js/**", "/img/**", "/fonts/**").permitAll()
//                        .requestMatchers("/", "/login", "/register").permitAll()
//                        .requestMatchers("/home").permitAll()
//                        .anyRequest().authenticated()
                        .anyRequest().permitAll()
                );
        return http.build();
    }
}
