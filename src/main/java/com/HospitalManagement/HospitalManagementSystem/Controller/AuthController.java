package com.HospitalManagement.HospitalManagementSystem.Controller;
import com.HospitalManagement.HospitalManagementSystem.Service.AuthService;
import com.HospitalManagement.HospitalManagementSystem.dto.LoginRequestDto;
import com.HospitalManagement.HospitalManagementSystem.dto.LoginResponseDto;
import com.HospitalManagement.HospitalManagementSystem.dto.SignUpRequestDto;
import com.HospitalManagement.HospitalManagementSystem.dto.SignUpResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signup(@RequestBody SignUpRequestDto signupRequestDto) {
        return ResponseEntity.ok(authService.signup(signupRequestDto));
    }
}