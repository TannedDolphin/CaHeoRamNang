package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.AuthenticationRequest;
import cayxanh.GreencareTest.dto.response.AuthenticationResponse;
import cayxanh.GreencareTest.entity.User;
import cayxanh.GreencareTest.exception.AppException;
import cayxanh.GreencareTest.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class AuthenticationServiceTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAuthenticateSuccess() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword(new BCryptPasswordEncoder().encode("password"));

        when(userRepo.findByUsername("testuser")).thenReturn(Optional.of(user));

        AuthenticationRequest request = new AuthenticationRequest();
        request.setUsername("testuser");
        request.setPassword("password");

        AuthenticationResponse response = authenticationService.authenticate(request);

        assertTrue(response.isAuthenticated());
        assertNotNull(response.getToken());
    }

    @Test
    public void testAuthenticateFailure() {
        when(userRepo.findByUsername("testuser")).thenReturn(Optional.empty());

        AuthenticationRequest request = new AuthenticationRequest();
        request.setUsername("testuser");
        request.setPassword("password");

        assertThrows(AppException.class, () -> {
            authenticationService.authenticate(request);
        });
    }
}

