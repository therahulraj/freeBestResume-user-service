package com.freebestresume.user_service.config.security;

import com.freebestresume.user_service.util.JwtTokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenUtils jwtTokenUtils;

    private UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtTokenUtils jwtTokenUtils, UserDetailsService userDetailsService) {
        this.jwtTokenUtils = jwtTokenUtils;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeaderToken = request.getHeader("Authorization");

        if (StringUtils.hasLength(authHeaderToken)) {

            String bearerToken = StringUtils.hasText(authHeaderToken) && authHeaderToken.length() > 7
                    && authHeaderToken.startsWith("Bearer ") ? authHeaderToken.substring(7) : null;

            if (bearerToken != null && jwtTokenUtils.validateToken(bearerToken)) {
                String username = jwtTokenUtils.getUsername(bearerToken);
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(userDetails);
                jwtAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(jwtAuthenticationToken);
//                return;
            }
            filterChain.doFilter(request, response);
            //send
            //invalid token structure, can contain only whitespace or any jebris.
            return;

        }
        filterChain.doFilter(request, response);
    }
}
