package com.example.EnglishSkillTrackerCRUD.auth;

import com.example.EnglishSkillTrackerCRUD.dto.UserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/loggin")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequestDTO.username(),
                loginRequestDTO.password()
        );
        var authenticatedUser = authenticationManager.authenticate(authenticationToken);

        String jwtToken = jwtService.generateToken(authenticatedUser.getName());

        return ResponseEntity.ok(new AuthResponseDTO(jwtToken, "Bearer", 3600L));
    }

    @PostMapping("/register")
    public String postMethodName(@RequestBody UserDTO userDTO) {


        return "userDTO";
    }
    @GetMapping("/profile")
    public UserDTO getProfile(@AuthenticationPrincipal UserDTO userDTO) {
        // Lógica para obtener el perfil del usuario
        // ...
        return userDTO;
    }

}
