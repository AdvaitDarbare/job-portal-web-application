package com.personal.jobportal.config;

import com.personal.jobportal.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Marks this class as a configuration source for the Spring context.
public class WebSecurityConfig {

    // Injected service classes managed by Spring's IoC container
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    // Constructor injection for dependencies
    @Autowired
    public WebSecurityConfig(CustomUserDetailsService customUserDetailsService,
                             CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.customUserDetailsService = customUserDetailsService;
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }

    // Array of URL patterns that are publicly accessible without authentication
    private final String[] publicUrl = {
            "/", "/global-search/**", "/register", "/register/**", "/webjars/**",
            "/resources/**", "/assets/**", "/css/**", "/summernote/**", "/js/**",
            "/*.css", "/*.js", "/*.js.map", "/fonts**", "/favicon.ico", "/resources/**", "/error"
    };

    // SecurityFilterChain bean definition to configure HTTP security
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Sets the authentication provider
        http.authenticationProvider(authenticationProvider());

        // Configure authorization requests
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers(publicUrl).permitAll();  // Allow public URLs without authentication
            auth.anyRequest().authenticated();  // Require authentication for any other requests
        });

        // Configure form login and logout behaviors
        http.formLogin(form->form.loginPage("/login").permitAll()  // Specifies the custom login page URL
                        .successHandler(customAuthenticationSuccessHandler))  // Uses the custom success handler
                .logout(logout-> {
                    logout.logoutUrl("/logout");  // Sets the logout URL
                    logout.logoutSuccessUrl("/");  // Redirect to homepage after logout
                }).cors(Customizer.withDefaults())  // Enables CORS with default settings
                .csrf(csrf->csrf.disable());  // Disables CSRF protection

        return http.build();
    }

    // AuthenticationProvider bean to handle authentication logic
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());  // Sets the password encoder
        authenticationProvider.setUserDetailsService(customUserDetailsService);  // Sets the custom user details service
        return authenticationProvider;
    }

    // PasswordEncoder bean to handle password encoding in the security context
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
