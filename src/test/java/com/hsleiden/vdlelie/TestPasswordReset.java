//package com.hsleiden.vdlelie;
//
//import com.hsleiden.vdlelie.dao.AccountRepository;
//import com.hsleiden.vdlelie.dto.ResetPassRequest;
//import com.hsleiden.vdlelie.model.Account;
//import com.hsleiden.vdlelie.model.Location;
//import com.hsleiden.vdlelie.model.Role;
//import com.hsleiden.vdlelie.services.*;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//
//import java.util.Optional;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class TestPasswordReset {
//
//    private AuthenticationService authenticationService;
//
//    @Mock
//    private AccountRepository accountRepository;
//
//    @Mock
//    private AccountServiceImpl accountService;
//
//    @Mock
//    private PasswordEncoder passwordEncoder;
//    @Mock
//    private JwtService jwtService;
//    @Mock
//    private LocationServiceImpl locationService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        authenticationService = new AuthenticationService(accountRepository, accountService, passwordEncoder, jwtService, locationService);
//    }
//
//    @Test
//    public void testResetPassword() {
//        // Arrange
//        String username = "ExampleUser";
//        String newPassword = "newPass";
//        Role role = Role.ROLE_ADMIN;
//        ResetPassRequest request = new ResetPassRequest(username, newPassword);
//
//        Account user = Account
//                .builder()
//                .employeenumber(1)
//                .name(username)
//                .password(passwordEncoder.encode(newPassword))
//                .role(role)
//                .location(new Location())
//                .build();;
//        when(accountRepository.findByName(username)).thenReturn(Optional.of(user));
//        when(passwordEncoder.encode(newPassword)).thenReturn("EncodedPass");
//
//        // Act
//        authenticationService.resetPassword(request);
//
//        // Assert
//        verify(accountRepository).findByName(username);
//        verify(accountRepository).save(user);
//        assertEquals("EncodedPass", user.getPassword());
//    }
//
//
//}
