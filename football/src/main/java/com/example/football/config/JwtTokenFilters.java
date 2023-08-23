package com.example.football.config;

import com.example.football.contraint.Header;
import com.example.football.entity.User;
import com.example.football.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JwtTokenFilters extends OncePerRequestFilter {

    private JwtTokenUtil jwtTokenUtil;
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(String.valueOf(Header.Authorization));
        String phone = null;
        String jwtToken = null;
        if (token != null && token.startsWith(String.valueOf(Header.Bearer))){
            jwtToken = token.substring(7);
            if (jwtTokenUtil.isTokenExpired(jwtToken)) {
                throw new ExpiredJwtException(null, null, "Jwt expired");
            }
            phone = jwtTokenUtil.getPhoneFromToken(jwtToken);

        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }
        if (phone != null){
            User user = userRepository.findByPhone(phone);
            request.setAttribute("phone",user.getPhone());
            String[] roleArr = user.getRole().split(",");
            List<SimpleGrantedAuthority> authorities = Arrays.stream(roleArr)
                    .map(role -> new SimpleGrantedAuthority(role))
                    .collect(Collectors.toList());

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    authorities);
            // After setting the Authentication in the context, we specify
            // that the current user is authenticated. So it passes the Spring Security Configurations successfully.
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request,response);

    }
}
