
package com.wzr26.onlineexam.config;

import com.wzr26.onlineexam.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        System.out.println("Authorization header present: " + (header != null));
        System.out.println("Authorization starts with Bearer: "
        + (header != null && header.startsWith("Bearer ")));

        // Không có Bearer token: để Security quyết định quyền truy cập.
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        try {
            Claims claims = jwtService.validateToken(token);

            String username = claims.getSubject();
            String role = claims.get("role", String.class);

            if (username == null || role == null || role.isBlank()) {
                response.sendError(
                        HttpServletResponse.SC_UNAUTHORIZED,
                        "Invalid token"
                );
                return;
            }

            var authorities = List.of(
                    new SimpleGrantedAuthority(
                            "ROLE_" + role.toUpperCase()
                    )
            );

            var authentication =
                    new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            authorities
                    );

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
                    System.out.println(
                        "JWT authenticated: " + username
                        + ", role: " + role
                        + ", authorities: " + authorities
                );

        } catch (JwtException | IllegalArgumentException e) {
            SecurityContextHolder.clearContext();

            response.sendError(
                    HttpServletResponse.SC_UNAUTHORIZED,
                    "Invalid or expired token"
            );
            return;
        }

        filterChain.doFilter(request, response);
    }
}
