package az.edurive.course.service.impl;

import az.edurive.course.exception.AlreadyExistsException;
import az.edurive.course.exception.NotFoundException;
import az.edurive.course.mapper.UserMapper;
import az.edurive.course.model.dto.request.LoginRequest;
import az.edurive.course.model.dto.request.UserRequest;
import az.edurive.course.model.dto.response.TokenResponse;
import az.edurive.course.model.dto.response.UserResponse;
import az.edurive.course.model.entity.User;
import az.edurive.course.model.enums.TokenType;
import az.edurive.course.repository.UserRepository;
import az.edurive.course.security.JwtTokenProvider;
import az.edurive.course.security.PasswordEncoder;
import az.edurive.course.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        try{
            Authentication authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getGmail(),
                            loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            TokenResponse tokenResponse=new TokenResponse();
            tokenResponse.setAccessToken(jwtTokenProvider.generateToken((UserDetails) authentication.getPrincipal(), TokenType.ACCESS_TOKEN));
            tokenResponse.setRefreshToken(jwtTokenProvider.generateToken((UserDetails) authentication.getPrincipal(),TokenType.REFRESH_TOKEN));
            return tokenResponse;
        }catch (InternalAuthenticationServiceException exception){
            log.error("User not found");
            throw new NotFoundException("User not found with this gmail: "+loginRequest.getGmail());
        }
    }

    @Override
    public TokenResponse refreshToken(UserDetails userDetails) {
        return null;
    }

    @Override
    public UserResponse registration(UserRequest userRequest) {
        userRepository.findByGmail(userRequest.getGmail()).ifPresent(user -> {
            throw new AlreadyExistsException("Registration is already exists with this email:"+user.getGmail());
        });
        User user=userMapper.requestToEntity(userRequest);
        user.setPassword(passwordEncoder.passwordEncode(userRequest.getPassword()));
        user.setRoleType(userRequest.getRoleType());
        return userMapper.entityToResponse(userRepository.save(user));
    }
}
