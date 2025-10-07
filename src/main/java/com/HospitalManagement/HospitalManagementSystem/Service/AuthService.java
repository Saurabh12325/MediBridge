package com.HospitalManagement.HospitalManagementSystem.Service;

import com.HospitalManagement.HospitalManagementSystem.Entity.Type.AuthProviderType;
import com.HospitalManagement.HospitalManagementSystem.Entity.User;
import com.HospitalManagement.HospitalManagementSystem.Repository.UserRepository;
import com.HospitalManagement.HospitalManagementSystem.Security.JwtUtil;
import com.HospitalManagement.HospitalManagementSystem.dto.LoginRequestDto;
import com.HospitalManagement.HospitalManagementSystem.dto.LoginResponseDto;
import com.HospitalManagement.HospitalManagementSystem.dto.SignUpRequestDto;
import com.HospitalManagement.HospitalManagementSystem.dto.SignUpResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager  authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
        User user = userRepository.findByUsername(signupRequestDto.getUsername()).orElse(null);
        if(user != null){
            throw new IllegalArgumentException("User already exists");
        }
        user = userRepository.save(User.builder()
                .username(signupRequestDto.getUsername())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .build());

        return new SignUpResponseDto(user.getId(), user.getUsername());
    }

    public ResponseEntity<LoginResponseDto> handleOAuth2LoginSucess(OAuth2User oAuth2User, String registrationId) {
//     first get the providerID and the ProviderType and then save the providerType and providerId in the user table
//        and if the user have an account:directly login
//                otherwise create an account and then login
    }

    public AuthProviderType getAuthProviderType(String registrationId){
        return switch (registrationId.toLowerCase()){
            case "google" ->AuthProviderType.GOOGLE;
            case "github" ->AuthProviderType.GITHUB;
            case "facebook" ->AuthProviderType.FACEBOOK;
            case "twitter" ->AuthProviderType.TWITTER;
            default -> throw  new IllegalArgumentException("Invalid registration id");
        };
    }
}

