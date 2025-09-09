package com.reznok.helloworld.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity // 1
public class SecurityConfig extends WebSecurityConfigurerAdapter { // 2

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Enable CORS and disable CSRF. 3 and 5
        http = http.cors().and().csrf().disable();

        // Set session management to stateless. 4
        http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

        // Set unauthorized requests exception handler. 6
        http = http.exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
        }).and();

        // Set permissions on endpoints. 7
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated();

        // Add JWT token filter. 8
        }

    // Used by spring security if CORS is enabled. 3
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(Arrays.asList("authorization",  "Authorization",
                "content-type","Content-Type", "Access-Control-Allow-Origin",
                "Access-Control-Allow-Headers", "Access-Control-Allow-Methods"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}