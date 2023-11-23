package com.hsleiden.vdlelie;

import com.hsleiden.vdlelie.dao.AccountRepository;
import com.hsleiden.vdlelie.model.*;
import com.hsleiden.vdlelie.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAccountsTest {

    @Mock
    private AccountRepository accountRepository;

    private AccountService SUT;

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private LocationServiceImpl locationService;

    @BeforeEach
    public void setup() {
        this.SUT = new AccountServiceImpl(accountRepository);
    }
    @Test
    public void should_return_all_accounts(){
        when(locationService.findById(anyString())).thenReturn(Optional.of(new Location("Adress", new Stock())));
        List<Account> dummyAccounts = Arrays.asList(
                new Account().builder()
                        .employeenumber(1)
                        .name("name")
                        .password(passwordEncoder.encode("admin"))
                        .role(Role.ROLE_ADMIN)
                        .location(locationService.findById("cdba1f68-f9e9-41c7-972e-0a12209763f4").get())
                        .build(),
                new Account().builder()
                        .employeenumber(1)
                        .name("name2")
                        .password(passwordEncoder.encode("admin2"))
                        .role(Role.ROLE_ADMIN)
                        .location(locationService.findById("cdba1f68-f9e9-41c7-972e-0a12209763f4").get())
                        .build()
        );

        when(this.accountRepository.findAll()).thenReturn(dummyAccounts);
        List<Account> actualAccounts = SUT.findAll();
        assertThat(actualAccounts.size(), is(dummyAccounts.size()));
    }

    @Test
    public void should_return_the_right_account_by_id(){
        when(locationService.findById(anyString())).thenReturn(Optional.of(new Location("Adress", new Stock())));
        Account dummyAccount = new Account()
                .builder()
                .employeenumber(1)
                .name("name")
                .password(passwordEncoder.encode("admin"))
                .role(Role.ROLE_ADMIN)
                .location(locationService.findById("cdba1f68-f9e9-41c7-972e-0a12209763f4").get())
                .build();
        String id = dummyAccount.getId();

        when(accountRepository.findById(id)).thenReturn(Optional.of(dummyAccount));

        Optional<Account> actualAccount = SUT.findById(id);

        assertThat(actualAccount.get().getName(), is(dummyAccount.getName()));
    }

}









