package com.example.demo4.SecurityApp.services;
import com.example.demo4.SecurityApp.dto.loginDto;
import com.example.demo4.SecurityApp.dto.loginResponseDTO;
import com.example.demo4.SecurityApp.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final SessionService sessionService;

    public loginResponseDTO Login(loginDto loginDto) {
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));
        User user = (User) authentication.getPrincipal();
        String accessToken =  jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        sessionService.generateNewSession(user,refreshToken);

        return new loginResponseDTO(user.getId(),accessToken,refreshToken);
    }

    public loginResponseDTO refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        sessionService.validateSession(refreshToken);

        User user = userService.getUserById(userId);
        String accessToken =  jwtService.generateAccessToken(user);
        return new loginResponseDTO(user.getId(),accessToken,refreshToken);
    }
}
