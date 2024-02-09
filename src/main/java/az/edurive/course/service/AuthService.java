package az.edurive.course.service;

import az.edurive.course.model.dto.request.LoginRequest;
import az.edurive.course.model.dto.request.UserRequest;
import az.edurive.course.model.dto.response.TokenResponse;
import az.edurive.course.model.dto.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
     TokenResponse login(LoginRequest loginRequest);
     TokenResponse refreshToken(UserDetails userDetails);
     UserResponse registration(UserRequest userRequest);
}
