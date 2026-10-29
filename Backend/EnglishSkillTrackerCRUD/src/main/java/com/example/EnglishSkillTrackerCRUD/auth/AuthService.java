package com.example.EnglishSkillTrackerCRUD.auth;

import com.example.EnglishSkillTrackerCRUD.dto.UserDTO;
import com.example.EnglishSkillTrackerCRUD.service.abstraction.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final AuthenticationManager authenticationManager;
    private final ITokenService tokenService;
    private IUserService userService;
    @Override
    public UserDTO register(UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequestDTO.username(),
                loginRequestDTO.password()
        );

        Authentication authenticatedUser =authenticationManager.authenticate(authenticationToken);
        String jwtToken = tokenService.generateToken(authenticatedUser.getName());
        return  new AuthResponseDTO(jwtToken,"Bearer",tokenService.getExpiration());
    }
}
