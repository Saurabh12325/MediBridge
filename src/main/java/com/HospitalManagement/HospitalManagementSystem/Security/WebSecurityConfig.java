package com.HospitalManagement.HospitalManagementSystem.Security;

import com.HospitalManagement.HospitalManagementSystem.Entity.Type.RoleType.*;
import com.HospitalManagement.HospitalManagementSystem.OAuth.OAuth2SuccessHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

import static com.HospitalManagement.HospitalManagementSystem.Entity.Type.PermissionType.APPOINTMENT_DELETE;
import static com.HospitalManagement.HospitalManagementSystem.Entity.Type.PermissionType.USER_MANAGE;
import static com.HospitalManagement.HospitalManagementSystem.Entity.Type.RoleType.*;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig{

    private final JwtAuthFilter authFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
//    private final  HandlerExceptionResolver handlerExceptionResolver;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerExceptionResolver handlerExceptionResolver) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->auth
                                .requestMatchers("/public/**","/auth/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/admin/**")
                        .hasAnyAuthority(APPOINTMENT_DELETE.name(),
                                USER_MANAGE.name())
                        .requestMatchers("/doctor/**").hasAnyRole(DOCTOR.name(),ADMIN.name())
                        .requestMatchers("/admin/**").hasRole(ADMIN.name())
                        .anyRequest().authenticated()
                )
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                 .oauth2Login(oauth2 -> oauth2
                         .failureHandler( (request, response, exception) -> {
                             handlerExceptionResolver.resolveException(request, response, null, exception);
                             log.error("aAuthError : {}", exception.getMessage());

                         })
                 .successHandler(oAuth2SuccessHandler)

                 )
                .exceptionHandling(exceptionHandlingConfig-> exceptionHandlingConfig.accessDeniedHandler((request, response, accessDeniedException) -> {
                    handlerExceptionResolver.resolveException(request, response, null, accessDeniedException);
                    log.error("Access denied: {}", accessDeniedException.getMessage());

                }))
        ;

        return http.build();


        }


}
