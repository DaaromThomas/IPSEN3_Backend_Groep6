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

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
@RequiredArgsConstructor
public class RefreshTokenController {
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    @PostMapping("/refreshtoken")
    public TokenRefreshResponse refreshtoken(@RequestBody @Valid TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        Optional<RefreshToken> optionalrefreshToken = refreshTokenService.findByToken(requestRefreshToken);
        if(optionalrefreshToken.isPresent()){
            RefreshToken refreshToken = optionalrefreshToken.get();
            this.refreshTokenService.verifyExpiration(refreshToken);
            String accessToken = jwtService.generateFromUsername(refreshToken.getAccount().getUsername());
            return new TokenRefreshResponse(accessToken, requestRefreshToken);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Refresh token not found");
    }

    @PostMapping("/refreshtoken/delete")
    public void deleteRefreshToken(@RequestBody @NotEmpty String token) {
        RefreshToken refreshToken = refreshTokenService.findByToken(token).get();
        this.refreshTokenService.deleteToken(refreshToken);
    }
}
