package com.pokemons.pokemons.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.headers().disable(); // to allow H2 console

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/console/**").anonymous()
                .antMatchers("/register").permitAll()
                .anyRequest().authenticated()

                .and()
                .formLogin().permitAll();


    }
}
