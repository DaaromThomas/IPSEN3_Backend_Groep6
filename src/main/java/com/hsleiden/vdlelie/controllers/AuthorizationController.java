package com.hsleiden.vdlelie.controllers;


import com.hsleiden.vdlelie.dto.*;
import com.hsleiden.vdlelie.exceptions.TokenRefreshException;
import com.hsleiden.vdlelie.model.RefreshToken;
import com.hsleiden.vdlelie.services.AuthenticationService;
import com.hsleiden.vdlelie.services.JwtService;
import com.hsleiden.vdlelie.services.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    @PreAuthorize("hasRole('ADMIN')")
    public JwtAuthenticationResponse signup(@RequestBody SignUpRequest request) {
        return authenticationService.signup(request);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest request){
        return authenticationService.signin(request);
    }

    @PatchMapping("/passwordreset")
    public void resetPassword(@RequestBody ResetPassRequest request){
        authenticationService.resetPassword(request);
    }
    }

