package az.edurive.course.controller;

import az.edurive.course.model.dto.request.LoginRequest;
import az.edurive.course.model.dto.request.UserRequest;
import az.edurive.course.model.dto.response.TokenResponse;
import az.edurive.course.model.dto.response.UserResponse;
import az.edurive.course.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request){
        return ResponseEntity.accepted().body(authService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refreshToken(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(authService.refreshToken(userDetails));
    }
    @PostMapping("/registration")
    public ResponseEntity<UserResponse> userRegistration(@Valid @RequestBody UserRequest userRequest){
        return ResponseEntity.ok(authService.registration(userRequest));
    }
}
