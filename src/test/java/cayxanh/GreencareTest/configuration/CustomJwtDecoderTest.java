package cayxanh.GreencareTest.configuration;

import cayxanh.GreencareTest.dto.request.IntrospectRequest;
import cayxanh.GreencareTest.dto.response.IntrospectResponse;
import cayxanh.GreencareTest.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomJwtDecoderTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private CustomJwtDecoder customJwtDecoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDecode_ValidToken() throws JOSEException, ParseException {
        String token = "valid.token.here";
        IntrospectResponse response = new IntrospectResponse();
        response.setValid(true);

        when(authenticationService.introspect(any(IntrospectRequest.class))).thenReturn(response);

        NimbusJwtDecoder nimbusJwtDecoder = mock(NimbusJwtDecoder.class);
        Jwt jwt = Jwt.withTokenValue(token)
                .header("alg", "HS512")
                .claim("sub", "1234567890")
                .issuedAt(new Date().toInstant())
                .expiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 10).toInstant())
                .build();

        customJwtDecoder.setNimbusJwtDecoder(nimbusJwtDecoder);
        when(nimbusJwtDecoder.decode(token)).thenReturn(jwt);

        Jwt decodedJwt = customJwtDecoder.decode(token);

        assertNotNull(decodedJwt);
        assertEquals("1234567890", decodedJwt.getClaim("sub"));
    }

    @Test
    void testDecode_InvalidToken() throws JOSEException, ParseException {
        String token = "invalid.token.here";
        IntrospectResponse response = new IntrospectResponse();
        response.setValid(false);

        when(authenticationService.introspect(any(IntrospectRequest.class))).thenReturn(response);

        assertThrows(JwtException.class, () -> customJwtDecoder.decode(token));
    }

    @Test
    void testDecode_NullToken() {
        assertThrows(JwtException.class, () -> customJwtDecoder.decode(null));
    }

    @Test
    void testDecode_EmptyToken() {
        assertThrows(JwtException.class, () -> customJwtDecoder.decode(""));
    }
}
