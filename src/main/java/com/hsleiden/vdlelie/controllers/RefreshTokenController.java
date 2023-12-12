package com.hsleiden.vdlelie.controllers;

import com.hsleiden.vdlelie.dto.TokenRefreshRequest;
import com.hsleiden.vdlelie.dto.TokenRefreshResponse;
import com.hsleiden.vdlelie.model.RefreshToken;
import com.hsleiden.vdlelie.services.JwtService;
import com.hsleiden.vdlelie.services.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class RefreshTokenController {
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    @PostMapping("/refreshtoken")
    public TokenRefreshResponse refreshtoken(@RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        Optional<RefreshToken> optionalrefreshToken = refreshTokenService.findByToken(requestRefreshToken);
        if(optionalrefreshToken.isPresent()){
            RefreshToken refreshToken = optionalrefreshToken.get();
            this.refreshTokenService.verifyExpiration(refreshToken);
            String accessToken = jwtService.generateFromUsername(refreshToken.getAccount().getUsername());
            return new TokenRefreshResponse(accessToken, requestRefreshToken);
        }
        return null;


    }
    @PostMapping("/refreshtoken/delete")
    public void deleteRefreshToken(@RequestBody String token) {
        RefreshToken refreshToken = refreshTokenService.findByToken(token).get();
        this.refreshTokenService.deleteToken(refreshToken);
    }
}
