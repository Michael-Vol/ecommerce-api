package com.michaelvol.ecommerceapi.security.filter;

import com.michaelvol.ecommerceapi.security.JwtService;
import com.michaelvol.ecommerceapi.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String jwtToken;
        final String email;
        final boolean isTokenValid;

        final String authenticationHeader = request.getHeader("Authorization");

        // Token Existence Check
        if (authenticationHeader == null || !authenticationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        // Security Context Check
        if (SecurityContextHolder.getContext().getAuthentication() != null){
            filterChain.doFilter(request, response);
            return;
        }


        jwtToken = authenticationHeader.substring(7);
        email = jwtService.getUsername(jwtToken);
        // Email Existence && Token Validity Check
        if (email.isEmpty() || jwtService.isTokenValid(jwtToken, email)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Set Authentication
        UserDetails userDetails = userService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        filterChain.doFilter(request, response);
        return;
    }
}
