package br.gov.ma.suinf.email.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthenticationFilter extends OncePerRequestFilter {

    private final ApiKeyAuthenticationProvider provider;

    public AuthenticationFilter(ApiKeyAuthenticationProvider provider) {
        this.provider = provider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String clientId = request.getHeader("X-CLIENT-ID");
        String apiKey = request.getHeader("X-API-KEY");

        if (clientId == null || apiKey == null || !provider.isValid(clientId, apiKey)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Acesso negado: chave inválida ou ausente");
            return;
        }

        
        SecurityContextHolder.getContext().setAuthentication(provider.getAuthentication(clientId));
        filterChain.doFilter(request, response);
    }
}
