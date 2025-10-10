package com.HospitalManagement.HospitalManagementSystem.Service;

import com.HospitalManagement.HospitalManagementSystem.Entity.Type.AuthProviderType;
import com.HospitalManagement.HospitalManagementSystem.Entity.Type.RoleType;
import com.HospitalManagement.HospitalManagementSystem.Entity.User;
import com.HospitalManagement.HospitalManagementSystem.OAuth.OAuthService;
import com.HospitalManagement.HospitalManagementSystem.Repository.UserRepository;
import com.HospitalManagement.HospitalManagementSystem.Security.JwtUtil;
import com.HospitalManagement.HospitalManagementSystem.dto.LoginRequestDto;
import com.HospitalManagement.HospitalManagementSystem.dto.LoginResponseDto;
import com.HospitalManagement.HospitalManagementSystem.dto.SignUpRequestDto;
import com.HospitalManagement.HospitalManagementSystem.dto.SignUpResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Set;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final OAuthService oAuthService;
    private final AuthenticationManager authenticationManager;
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

    public User signUpInternal(SignUpRequestDto signUpRequestDto,AuthProviderType authProviderType ,String providerId){
        User user = userRepository.findByUsername(signUpRequestDto.getUsername()).orElse(null);
        if(user != null){
            throw new IllegalArgumentException("User already exists ");
        }
        user =User.builder()
                .username(signUpRequestDto.getUsername())
                .providerType(authProviderType)
                .providerId(providerId)
                .roles(Set.of(RoleType.PATIENT))
                .build();
        if(authProviderType == AuthProviderType.EMAIL){
            user.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        }
        return userRepository.save(user);
    }
    public SignUpResponseDto signup(SignUpRequestDto signupRequestDto) {
        User user = signUpInternal(signupRequestDto,AuthProviderType.EMAIL,null);
        return new SignUpResponseDto(user.getId(), user.getUsername());
    }


@Transactional
    public ResponseEntity<LoginResponseDto> handleOAuth2LoginSucess(OAuth2User oAuth2User, String registrationId) {

//     first get the providerID and the ProviderType and then save the providerType and providerId in the user table
        AuthProviderType authProviderType = oAuthService.getAuthProviderType(registrationId);
        String providerId = oAuthService.getProviderIdFromOAuthUser(oAuth2User,registrationId);
        User user = userRepository.findByProviderIdAndProviderType(providerId,authProviderType).orElse(null);

        String email = oAuth2User.getAttribute("email");
        User emailUser = userRepository.findByUsername(email).orElse(null);

        if(user == null && emailUser == null){ //agar user nahi hai toh create karega
            String username = oAuthService.determineUserNameFromOAuthUser(oAuth2User, registrationId, providerId);
           user = signUpInternal(new SignUpRequestDto(username,null),authProviderType,providerId);

        } else if (user != null) {
            if ((email !=null && !email.isBlank() && !email.equals(user.getUsername()))){
                user.setUsername(email);
                userRepository.save(user);
            }

        }
        else {
            throw new BadCredentialsException("This email already registered with provider " + email);
        }
        LoginResponseDto loginResponseDto = new LoginResponseDto(jwtUtil.generateAccessToken(user), user.getId());
        return  ResponseEntity.ok(loginResponseDto);
//        and if the user have an account:directly login
//                otherwise create an account and then login
    }


}

