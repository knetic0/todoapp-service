package com.pieces.todoapp.filters;

import com.pieces.todoapp.core.security.jwt.ITokenHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {
    private final ITokenHelper tokenHelper;
    private final UserDetailsService userDetailsService;

    private final static String AUTHORIZATION_HEADER = "Authorization";
    private final static String BEARER_PREFIX = "Bearer ";

    public AuthenticationFilter(ITokenHelper tokenHelper, UserDetailsService userDetailsService) {
        this.tokenHelper = tokenHelper;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException, NullPointerException {
        final String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

        if(authorizationHeader == null || !authorizationHeader.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authorizationHeader.substring(BEARER_PREFIX.length() + 1);
        final String username = tokenHelper.extractUsername(token);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(username != null && authentication != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(tokenHelper.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                request.setAttribute("user", userDetails);
            }
        }

        filterChain.doFilter(request, response);
    }
}
