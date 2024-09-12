package cayxanh.GreencareTest.configuration;

import cayxanh.GreencareTest.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SecurityConfigTest {

    @Mock
    private CustomJwtDecoder customJwtDecoder;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private SecurityConfig securityConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFilterChain() throws Exception {
        HttpSecurity httpSecurity = mock(HttpSecurity.class);
        SecurityFilterChain securityFilterChain = securityConfig.filterChain(httpSecurity);

        verify(httpSecurity).authorizeHttpRequests(any());
        verify(httpSecurity).oauth2ResourceServer(any());
        verify(httpSecurity).csrf(any());
    }

    @Test
    void testJwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = securityConfig.jwtAuthenticationConverter();
        assertNotNull(jwtAuthenticationConverter);
    }

    @Test
    void testPasswordEncoder() {
        assertNotNull(securityConfig.passwordEncoder());
    }
}
