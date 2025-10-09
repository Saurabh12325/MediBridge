package com.HospitalManagement.HospitalManagementSystem.OAuth;

import com.HospitalManagement.HospitalManagementSystem.Entity.Type.AuthProviderType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuthService {
    public AuthProviderType getAuthProviderType(String registrationId){
        return switch (registrationId.toLowerCase()){
            case "google" ->AuthProviderType.GOOGLE;
            case "github" ->AuthProviderType.GITHUB;
            case "facebook" ->AuthProviderType.FACEBOOK;
            case "twitter" ->AuthProviderType.TWITTER;
            default -> throw  new IllegalArgumentException("Invalid registration id");
        };
    }

    public String getProviderIdFromOAuthUser(OAuth2User oAuth2User, String registrationId){
        String providerId = switch (registrationId.toLowerCase()) {
            case "google" -> oAuth2User.getAttribute("sub");
            case "github" -> {
                Object githubId = oAuth2User.getAttribute("id");
                yield String.valueOf(githubId);
            }
            default -> {
                log.error("Unsupported oAuth2 provider: {}", registrationId);
                throw new IllegalArgumentException("Invalid registration id: " + registrationId);
            }
        };

        if(providerId==null || providerId.isBlank()){
            log.error("Unable to determine providerId for provider:{}",registrationId);
            throw new IllegalArgumentException("Unable to determine providerId for OAuth2 Login");
        }
        return providerId;
    }

    public String determineUserNameFromOAuthUser(OAuth2User oAuth2User,String registrationId,String providerId){
        String email = oAuth2User.getAttribute("email");
        if(email !=null && !email.isBlank()){
            return email;
        }
        return switch (registrationId.toLowerCase()) {
            case "google" ->oAuth2User.getAttribute("sub");
            case  "github" ->oAuth2User.getAttribute("login");
            default -> providerId;
        };
    }
}
