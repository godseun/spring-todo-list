package com.godseun.todolistspring.config;

import com.godseun.todolistspring.security.JwtAuthenticationFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
public class WebSecurityConfig {

  @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;

  @Bean
  protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors()
        .and()
        .csrf().disable()
        .httpBasic().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests().antMatchers("/", "/auth/**", "/error").permitAll()
        .anyRequest().authenticated();

    http.addFilterAfter(
        jwtAuthenticationFilter,
        CorsFilter.class);

    return http.build();
  }
}
