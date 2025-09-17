package com.ae.poc.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class SessionAuthFilter extends OncePerRequestFilter {

    private static final List<String> EXCLUDED_PATHS = Arrays.asList(
        "/api/auth/",
        "/poc/public/",
        "/error"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                  HttpServletResponse response, 
                                  FilterChain filterChain) 
                                  throws ServletException, IOException {
        
        String path = request.getRequestURI();
        String method = request.getMethod();
        
        System.out.println("Filter processing: " + method + " " + path);
        
        // Allow OPTIONS requests for CORS preflight
        if ("OPTIONS".equalsIgnoreCase(method)) {
            System.out.println("Allowing OPTIONS preflight request");
            filterChain.doFilter(request, response);
            return;
        }

        // Check if path is excluded from authentication
        if (isExcludedPath(path)) {
            System.out.println("Path excluded from authentication: " + path);
            filterChain.doFilter(request, response);
            return;
        }

        // Check authentication for /poc/ endpoints
        if (path.contains("/poc/")) {
            System.out.println("Checking authentication for: " + path);
            
            // Check for JWT token first
            String authHeader = request.getHeader("Authorization");
            System.out.println("Authorization header: " + authHeader);
            
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                System.out.println("Token found: " + (token.length() > 20 ? token.substring(0, 20) + "..." : token));
                
                if (isValidToken(token)) {
                    System.out.println("Token is valid, allowing request");
                    filterChain.doFilter(request, response);
                    return;
                } else {
                    System.out.println("Token is invalid");
                }
            } else {
                System.out.println("No valid Authorization header found");
            }
            
            // Fall back to session authentication
            HttpSession session = request.getSession(false);
            Boolean authenticated = session != null ? (Boolean) session.getAttribute("authenticated") : false;
            System.out.println("Session authenticated: " + authenticated);

            if (authenticated != null && authenticated) {
                System.out.println("Session authentication successful");
                filterChain.doFilter(request, response);
            } else {
                System.out.println("Authentication failed, sending 401");
                sendUnauthorizedResponse(response);
                return;
            }
        } else {
            // For non-/poc/ endpoints, proceed without authentication
            System.out.println("Non-/poc/ endpoint, allowing without auth: " + path);
            filterChain.doFilter(request, response);
        }
    }

    private boolean isExcludedPath(String path) {
        return EXCLUDED_PATHS.stream().anyMatch(path::startsWith);
    }

    private boolean isValidToken(String token) {
        // Simple validation - accept any token that starts with "generated-token"
        return token != null && token.startsWith("generated-token");
    }

    private void sendUnauthorizedResponse(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.getWriter().write("{\"message\": \"Authentication required\", \"error\": \"UNAUTHORIZED\"}");
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        boolean shouldNotFilter = isExcludedPath(path) || "OPTIONS".equalsIgnoreCase(request.getMethod());
        System.out.println("shouldNotFilter for " + path + ": " + shouldNotFilter);
        return shouldNotFilter;
    }
}