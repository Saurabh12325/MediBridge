package com.HospitalManagement.HospitalManagementSystem.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter  {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("incoming request" + request.getRequestURI());

        final  String getAuthorizationHeader = request.getHeader("Authorization");
        if(getAuthorizationHeader == null && !getAuthorizationHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
    }
}
