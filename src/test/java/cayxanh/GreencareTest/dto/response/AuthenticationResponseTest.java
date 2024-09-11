package cayxanh.GreencareTest.dto.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthenticationResponseTest {

    @Test
    void testAuthenticationResponseBuilder() {
        AuthenticationResponse response = AuthenticationResponse.builder()
                .token("sampleToken")
                .authenticated(true)
                .build();

        assertNotNull(response);
        assertEquals("sampleToken", response.getToken());
        assertTrue(response.isAuthenticated());
    }

    @Test
    void testAuthenticationResponseNoArgsConstructor() {
        AuthenticationResponse response = new AuthenticationResponse();

        assertNotNull(response);
        assertNull(response.getToken());
        assertFalse(response.isAuthenticated());
    }

    @Test
    void testAuthenticationResponseAllArgsConstructor() {
        AuthenticationResponse response = new AuthenticationResponse("sampleToken", true);

        assertNotNull(response);
        assertEquals("sampleToken", response.getToken());
        assertTrue(response.isAuthenticated());
    }

    @Test
    void testAuthenticationResponseSettersAndGetters() {
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken("sampleToken");
        response.setAuthenticated(true);

        assertEquals("sampleToken", response.getToken());
        assertTrue(response.isAuthenticated());
    }
}
