package com.gl.ceir.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .and()
                .headers()
                .httpStrictTransportSecurity() // Enable HSTS header
                .includeSubDomains(true) // Include subdomains in HSTS
                .preload(false)
                .maxAgeInSeconds(Integer.MAX_VALUE)
                .requestMatcher(AnyRequestMatcher.INSTANCE)
                .and()
                .xssProtection()
                .and()
                .frameOptions().sameOrigin()
        ;
    }

}
