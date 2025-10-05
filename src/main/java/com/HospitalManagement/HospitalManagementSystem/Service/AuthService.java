package com.HospitalManagement.HospitalManagementSystem.Service;

import com.HospitalManagement.HospitalManagementSystem.Entity.User;
import com.HospitalManagement.HospitalManagementSystem.Security.JwtUtil;
import com.HospitalManagement.HospitalManagementSystem.dto.LoginRequestDto;
import com.HospitalManagement.HospitalManagementSystem.dto.LoginResponseDto;
import com.HospitalManagement.HospitalManagementSystem.dto.SignUpRequestDto;
import com.HospitalManagement.HospitalManagementSystem.dto.SignUpResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager  authenticationManager;
    private final JwtUtil jwtUtil;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()
                )
        );
        User user = (User) authentication.getPrincipal();
        String accessToken = jwtUtil.generateAccessToken(user);
        return new LoginResponseDto(accessToken, user.getId());
    }

    public SignUpResponseDto signup(SignUpRequestDto signupRequestDto) {
        return null;
    }
}
