package com.hsleiden.vdlelie.services;
import com.hsleiden.vdlelie.dao.AccountRepository;
import com.hsleiden.vdlelie.dto.JwtAuthenticationResponse;
import com.hsleiden.vdlelie.dto.ResetPassRequest;
import com.hsleiden.vdlelie.dto.SignInRequest;
import com.hsleiden.vdlelie.dto.SignUpRequest;
import com.hsleiden.vdlelie.model.Account;
import com.hsleiden.vdlelie.model.RefreshToken;
import com.hsleiden.vdlelie.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.beans.Encoder;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AccountRepository accountRepository;
    private final RefreshTokenService refreshTokenService;
    private final AccountServiceImpl accountService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final LocationServiceImpl locationService;

    public JwtAuthenticationResponse signup(SignUpRequest request) {

        if (accountRepository.findByName(request.getUsername()).isPresent()){ return null; }
        var user = Account
                .builder()
                .employeenumber(request.getEmployeenumber())
                .name(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .location(locationService.findById(request.getLocationID()).get())
                .role(Role.ROLE_USER)
                .build();

        user = accountService.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    public void resetPassword(ResetPassRequest request){
        var user = accountRepository.findByName(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username."));
        user.setPassword(passwordEncoder.encode(request.getNewpassword()));
        accountRepository.save(user);
    }


    public ResponseEntity<JwtAuthenticationResponse> signin(SignInRequest request) {
        var userFromRepo = accountRepository.findByName(request.getUsername());
        if(userFromRepo.isEmpty()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        Account user = userFromRepo.get();
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) { throw new ResponseStatusException(HttpStatus.UNAUTHORIZED); }
        var jwt = jwtService.generateToken(user);
         var refreshToken = new RefreshToken();
        try{
             refreshToken = refreshTokenService.createRefreshToken(user.getUsername());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found.");
        }
        
        return ResponseEntity.ok(JwtAuthenticationResponse.builder().token(jwt).refreshToken(refreshToken.getToken()).build());
    }

}
