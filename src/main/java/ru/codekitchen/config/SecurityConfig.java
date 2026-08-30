package ru.codekitchen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**", "/images/**","/fragments/**", "/private/**").permitAll()

                        .requestMatchers("/", "/login", "/registration", "/error","/place/**", "/add-place-page").permitAll()

                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login") // Укажем, что мы хотим свою страницу логина
                        .permitAll()
                );
        return http.build();

    }


}
