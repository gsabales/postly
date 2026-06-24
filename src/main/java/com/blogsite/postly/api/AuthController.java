package com.blogsite.postly.api;

import com.blogsite.postly.config.JwtUtility;
import com.blogsite.postly.model.rest.AuthRequest;
import com.blogsite.postly.model.rest.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController
{
    private final AuthenticationManager authenticationManager;
    private final JwtUtility jwtUtility;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request)
    {
        String encryptedPassword = passwordEncoder.encode(request.getPassword());
//        System.out.println("-- PASSSWORD: " + encryptedPassword);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        String token = jwtUtility.generateToken(request.getUsername());

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
