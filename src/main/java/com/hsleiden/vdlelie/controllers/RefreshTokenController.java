package com.hsleiden.vdlelie.controllers;

import com.hsleiden.vdlelie.dto.TokenRefreshRequest;
import com.hsleiden.vdlelie.dto.TokenRefreshResponse;
import com.hsleiden.vdlelie.model.RefreshToken;
import com.hsleiden.vdlelie.services.JwtService;
import com.hsleiden.vdlelie.services.RefreshTokenService;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;


import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequiredArgsConstructor
public class RefreshTokenController {
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@RequestBody @Valid TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        Optional<RefreshToken> optionalrefreshToken = refreshTokenService.findByToken(requestRefreshToken);
        if(optionalrefreshToken.isPresent()){
            RefreshToken refreshToken = optionalrefreshToken.get();
            this.refreshTokenService.verifyExpiration(refreshToken);
            String accessToken = jwtService.generateFromUsername(refreshToken.getAccount().getUsername());
            TokenRefreshResponse response = new TokenRefreshResponse(accessToken, requestRefreshToken);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/refreshtoken")
    public void deleteRefreshToken(@RequestParam String refreshToken) {
        System.out.println("deleteRefreshToken " + refreshToken);
         RefreshToken refreshTokenfromDB = refreshTokenService.findByToken(refreshToken).get();
         this.refreshTokenService.deleteToken(refreshTokenfromDB);
    }
}