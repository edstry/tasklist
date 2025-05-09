package com.edstry.Tasklist.web.security;

import com.edstry.Tasklist.domain.exception.ResourceNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
/*
Как я понял этот фильтр выполняется уже после аутентификации
когда мы получили токен и хотим выполнить внутренние запросы,
которые находятся под защитой spring security

 */
@AllArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {
        String bearerToken = ((HttpServletRequest) servletRequest).getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken = bearerToken.substring(7);
        }
        if(bearerToken != null && jwtTokenProvider.validateToken(bearerToken)) {
            try {
                Authentication authentication = jwtTokenProvider.getAuthentication(bearerToken);
                if(authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (ResourceNotFoundException ignored) {}
        }
        // Передаёт управление следующему фильтру
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
