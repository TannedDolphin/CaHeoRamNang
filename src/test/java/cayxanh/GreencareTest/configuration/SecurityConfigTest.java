package cayxanh.GreencareTest.configuration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class SecurityConfigTest {

    @Mock
    HttpSecurity httpSecurity;

    @InjectMocks
    SecurityConfig securityConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFilterChain() throws Exception {
        SecurityFilterChain filterChain = securityConfig.filterChain(httpSecurity);
        Assertions.assertNotNull(filterChain);
    }

    @Test
    void testJwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = securityConfig.jwtAuthenticationConverter();
        assertNotNull(converter);
    }

    @Test
    void testJwtDecoder() {
        JwtDecoder decoder = securityConfig.jwtDecoder();
        assertNotNull(decoder);
    }

    @Test
    void testPasswordEncoder() {
        assertNotNull(securityConfig.passwordEncoder());
    }
}
