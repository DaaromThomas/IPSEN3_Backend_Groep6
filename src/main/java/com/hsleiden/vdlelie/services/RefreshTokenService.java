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

import javax.security.auth.login.AccountNotFoundException;

@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final AccountRepository accountRepository;
    private final Long refreshTokenDurationMs;

    @Autowired
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, AccountRepository accountRepository, @Value("${jwt.refresh.expiration.ms}") Long refreshTokenDurationMs) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.accountRepository = accountRepository;
        this.refreshTokenDurationMs = refreshTokenDurationMs;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(String accountName) throws AccountNotFoundException {
        RefreshToken refreshToken = new RefreshToken();
        Optional<Account> optionalAccount = accountRepository.findByName(accountName);
        if(optionalAccount.isEmpty()){
            throw new AccountNotFoundException("Account not found: " + accountName);
        }
        Account account = optionalAccount.get();
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

    public void deleteToken(RefreshToken refreshToken){
        refreshTokenRepository.delete(refreshToken);
    }
}
