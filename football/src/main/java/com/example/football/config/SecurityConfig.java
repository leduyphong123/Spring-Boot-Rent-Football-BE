package com.example.football.config;

import com.example.football.contraint.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    @Autowired
    public JwtTokenFilters jwtTokenFilters;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests().antMatchers("/login").permitAll();
        http.authorizeHttpRequests().antMatchers("/api/home/**").permitAll();
        http.authorizeHttpRequests().antMatchers("/api/is-buisiness/**").permitAll();
        http.authorizeHttpRequests().antMatchers("/api/buisiness/**").permitAll();
        http.authorizeHttpRequests().antMatchers("/api/register-buisiness/*").hasRole(String.valueOf(Role.USER));
        http.authorizeHttpRequests().antMatchers("/api/buisiness/user/**").hasRole(String.valueOf(Role.BUISINESS));
        http.authorizeHttpRequests().antMatchers("/api/buisiness-status/*").hasRole(String.valueOf(Role.BUISINESS));
        http.authorizeHttpRequests().antMatchers("/api/pitch/edit/**").hasRole(String.valueOf(Role.BUISINESS));
        http.authorizeHttpRequests().antMatchers("/api/pitch/status/**").hasRole(String.valueOf(Role.BUISINESS));
        http.authorizeHttpRequests().antMatchers("/api/pitch/register/**").hasRole(String.valueOf(Role.BUISINESS));
        http.authorizeHttpRequests().antMatchers("/api/rent/create/**").hasAnyRole(String.valueOf(Role.USER),String.valueOf(Role.BUISINESS));
        http.authorizeHttpRequests().antMatchers("/api/rent/history/**").hasAnyRole(String.valueOf(Role.BUISINESS),String.valueOf(Role.USER));
//        http.authorizeHttpRequests().antMatchers("/api/buy").hasRole(String.valueOf(Role.ADMIN));
//        http.authorizeHttpRequests()
//                .antMatchers("/api/register-seller")
//                .hasAnyRole(String.valueOf(Role.USER),String.valueOf(Role.ADMIN));


//        http.addFilterBefore()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.DELETE)
//                .hasRole("ADMIN")
//                .antMatchers("/admin/**")
//                .hasAnyRole("ADMIN")
//                .antMatchers("/user/**")
//                .hasAnyRole("USER", "ADMIN")
//                .antMatchers("/login/**")
//                .anonymous()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtTokenFilters, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}