package org.subhankar.authservice.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final UserDetailsServiceImpl userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            response.sendError(401, "Unauthorized");
            return;
        }
        token = token.substring(7);
        String user = jwtProvider.getUsernameFromToken(token);
        if (user == null ) {
            response.sendError(401, "Unauthorized");
            return;
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(user);

        filterChain.doFilter(request, response);
    }
}
