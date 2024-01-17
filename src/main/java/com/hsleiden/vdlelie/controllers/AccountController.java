package com.hsleiden.vdlelie.controllers;

import com.hsleiden.vdlelie.dao.AccountRepository;
import com.hsleiden.vdlelie.model.Account;
import com.hsleiden.vdlelie.model.Location;
import com.hsleiden.vdlelie.services.AccountService;
import com.hsleiden.vdlelie.services.JwtService;
import com.hsleiden.vdlelie.services.LocationService;
import com.hsleiden.vdlelie.model.Role;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {
    private final AccountService accountService;

    private final AccountRepository accountRepository;
    private final LocationService locationService;
    private final JwtService jwtService;

    public AccountController(AccountService accountService, LocationService locationService, AccountRepository accountRepository, JwtService jwtService) {
        this.accountService = accountService;
        this.locationService = locationService;
        this.accountRepository = accountRepository;
        this.jwtService = jwtService;
    }

    @GetMapping("/accounts")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<Account> getAllAccounts() {
        return accountService.findAll();
    }

    @GetMapping("/accounts/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Account getAccountById(@PathVariable String id) {
        if (accountService.findById(id).isPresent()) {
            return accountService.findById(id).get();
        } else {
            return null;
        }
    }

    @GetMapping("/accounts/name")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Account getAccountByName(@RequestParam String name) {
        if (accountRepository.findByName(name).isPresent()) {
            return accountRepository.findByName(name).get();
        } else {
            return null;
        }
    }


    @DeleteMapping("/accounts/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAccount(@PathVariable String id) {
        if (accountService.findById(id).isPresent()) {
            Account account = accountService.findById(id).get();
            accountService.delete(account);
        }
        else {
            return;
        }
    }

    @PatchMapping("/accounts/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateAccount(@PathVariable String id, @RequestParam(required = false) String name, @RequestParam(required = false) Role role) {
        Optional<Account> possibleAccount = accountService.findById(id);
        Location location = null;
        if (possibleAccount.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }

        Account account = possibleAccount.get();

        if (name != null){
            account.setName(name);
        }

        if (location != null){
            account.setLocation(location);
        }
      
        if (role != null){
            account.setRole(role);
        }

        accountService.save(account);
    }

    @GetMapping("/currentuser")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String currentUser(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return jwtService.extractUserName(token);
    }

}
