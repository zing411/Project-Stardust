package com.group.Stardust.config;

import com.group.Stardust.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebMvc
public class SecurityConfig {

    private final MyUserDetailsService userDetailsService;

    public SecurityConfig(MyUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    // defining the security chain to configure to security rule
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                // establishing authorizations
                .authorizeHttpRequests((authorize) -> authorize
                        // anyone can access
                        .requestMatchers("/stardust/home", "/login/**", "/register/**").permitAll()
                        // anyone can access with USER role
                        .requestMatchers("/stardust/store/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                // Redirect to home after successful login
                .formLogin(httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer
                            .loginPage("/login")
                            .defaultSuccessUrl("/stardust/home", true)
                            .permitAll();
                })
                .logout(httpSecurityLogoutConfigurer -> {
                    httpSecurityLogoutConfigurer.logoutUrl("/logout").permitAll();
                });

        return http.build();
    }

    // handles retrieving user from a database for future use
    @Bean
    public DaoAuthenticationProvider httpSecurityFormLoginConfigurer() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
    // configure security to ignore certain resources, such as static files and h2 database
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (webSecurity) -> webSecurity.ignoring().requestMatchers("/css/**", "/h2-console/**");
    }

    // define password encoder bean using BCrypt hashing algorithm
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
