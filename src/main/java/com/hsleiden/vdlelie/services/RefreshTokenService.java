package com.hsleiden.vdlelie.services;

import com.hsleiden.vdlelie.dao.AccountRepository;
import com.hsleiden.vdlelie.dao.RefreshTokenRepository;
import com.hsleiden.vdlelie.exceptions.TokenRefreshException;
import com.hsleiden.vdlelie.model.Account;
import com.hsleiden.vdlelie.model.RefreshToken;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Value("${jwt.refresh.expiration.ms}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(String accountName) {
        RefreshToken refreshToken = new RefreshToken();
        if(accountRepository.findByName(accountName).isEmpty()){
            return null;
            //todo fix null return
        }
       Account account = accountRepository.findByName(accountName).get();
        Optional<RefreshToken> currentRefreshToken = refreshTokenRepository.findByAccount(account);
        currentRefreshToken.ifPresent(token -> refreshTokenRepository.delete(token));
        refreshToken.setAccount(account);
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }
}
