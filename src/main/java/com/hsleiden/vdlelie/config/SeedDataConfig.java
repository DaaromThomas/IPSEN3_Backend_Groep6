package com.hsleiden.vdlelie.config;

import com.hsleiden.vdlelie.dao.AccountRepository;
import com.hsleiden.vdlelie.model.Account;
import com.hsleiden.vdlelie.model.Location;
import com.hsleiden.vdlelie.model.Role;
import com.hsleiden.vdlelie.services.AccountServiceImpl;
import com.hsleiden.vdlelie.services.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountServiceImpl accountService;

    private final LocationService locationService;

    @Override
    public void run(String... args) throws Exception {
        if (accountRepository.count() == 0){
            Optional<Location> location = locationService.findById("cdba1f68-f9e9-41c7-972e-0a12209763f4");

            if (location.isEmpty())
                return; // Causes errors otherwise during testing

            Account admin = Account
                    .builder()
                    .employeenumber(1)
                    .name("name")
                    .password(passwordEncoder.encode("admin"))
                    .role(Role.ROLE_ADMIN)
                    .location(location.get())
                    .build();
            accountService.save(admin);
            log.debug("created admin account - {}", admin);
        }
    }

}
