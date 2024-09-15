package cayxanh.GreencareTest.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationRequestTest {

    @Test
    public void testDefaultConstructor() {
        AuthenticationRequest request = new AuthenticationRequest();
        assertNull(request.getUsername());
        assertNull(request.getPassword());
    }

    @Test
    public void testAllArgsConstructor() {
        AuthenticationRequest request = new AuthenticationRequest("user", "pass");
        assertEquals("user", request.getUsername());
        assertEquals("pass", request.getPassword());
    }

    @Test
    public void testSettersAndGetters() {
        AuthenticationRequest request = new AuthenticationRequest();
        request.setUsername("user");
        request.setPassword("pass");

        assertEquals("user", request.getUsername());
        assertEquals("pass", request.getPassword());
    }

    @Test
    public void testBuilder() {
        AuthenticationRequest request = AuthenticationRequest.builder()
                .username("user")
                .password("pass")
                .build();

        assertEquals("user", request.getUsername());
        assertEquals("pass", request.getPassword());
    }
}
