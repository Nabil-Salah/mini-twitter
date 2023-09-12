package com.minitwitter.minitwitter.auth.security;

import com.minitwitter.minitwitter.auth.service.MyUserDetailsService;
import lombok.AllArgsConstructor;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final MyUserDetailsService myUserDetailsService;
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( request -> request
                        .requestMatchers("/connections/**").permitAll()
                        .requestMatchers("/profile/**").permitAll()
                        .requestMatchers("/register/**").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers("/confirm-account/**").permitAll()
                        .anyRequest().authenticated()
                )
                .cors(withDefaults())
                .userDetailsService(myUserDetailsService)
                .formLogin(form -> form
                        .defaultSuccessUrl("/home",true)
                )
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowCredentials(true);
        config.setAllowedMethods(List.of("GET","POST","DELETE","PUT"));
        //config.setAllowedHeaders(List.of("Authorization","Access-Control-Allow-Origin"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",config);
        return source;
    }


}
