package com.hsleiden.vdlelie.controllers;


import com.hsleiden.vdlelie.dto.JwtAuthenticationResponse;
import com.hsleiden.vdlelie.dto.ResetPassRequest;
import com.hsleiden.vdlelie.dto.SignInRequest;
import com.hsleiden.vdlelie.dto.SignUpRequest;
import com.hsleiden.vdlelie.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
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
    public JwtAuthenticationResponse signIn(@RequestBody SignInRequest request){
        return authenticationService.signin(request);
    }

    @PatchMapping("/passwordreset")
    public void resetPassword(@RequestBody ResetPassRequest request){
        authenticationService.resetPassword(request);
    }


}
