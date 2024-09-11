package cayxanh.GreencareTest.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthenticationRequestTest {

    @Test
    void testAuthenticationRequestBuilder() {
        AuthenticationRequest request = AuthenticationRequest.builder()
                .username("user")
                .password("pass")
                .build();

        assertNotNull(request);
        assertEquals("user", request.getUsername());
        assertEquals("pass", request.getPassword());
    }

    @Test
    void testAuthenticationRequestNoArgsConstructor() {
        AuthenticationRequest request = new AuthenticationRequest();

        assertNotNull(request);
        assertNull(request.getUsername());
        assertNull(request.getPassword());
    }

    @Test
    void testAuthenticationRequestAllArgsConstructor() {
        AuthenticationRequest request = new AuthenticationRequest("user", "pass");

        assertNotNull(request);
        assertEquals("user", request.getUsername());
        assertEquals("pass", request.getPassword());
    }

    @Test
    void testAuthenticationRequestSettersAndGetters() {
        AuthenticationRequest request = new AuthenticationRequest();
        request.setUsername("user");
        request.setPassword("pass");

        assertEquals("user", request.getUsername());
        assertEquals("pass", request.getPassword());
    }
}
