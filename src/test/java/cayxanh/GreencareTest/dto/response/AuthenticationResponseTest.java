package cayxanh.GreencareTest.dto.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationResponseTest {

    @Test
    public void testNoArgsConstructor() {
        // Kiểm tra constructor không có tham số
        AuthenticationResponse response = new AuthenticationResponse();
        assertNull(response.getToken(), "Token should be null after no-args constructor");
        assertFalse(response.isAuthenticated(), "Authenticated should be false after no-args constructor");
    }

    @Test
    public void testAllArgsConstructor() {
        // Kiểm tra constructor có tham số
        AuthenticationResponse response = new AuthenticationResponse("sampleToken", true);
        assertEquals("sampleToken", response.getToken(), "Token should be set via all-args constructor");
        assertTrue(response.isAuthenticated(), "Authenticated should be set via all-args constructor");
    }

    @Test
    public void testSettersAndGetters() {
        // Kiểm tra setter và getter
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken("newToken");
        response.setAuthenticated(true);

        assertEquals("newToken", response.getToken(), "Token should be set and retrieved correctly");
        assertTrue(response.isAuthenticated(), "Authenticated should be set and retrieved correctly");
    }

    @Test
    public void testBuilderPattern() {
        // Kiểm tra builder pattern
        AuthenticationResponse response = AuthenticationResponse.builder()
                .token("builderToken")
                .authenticated(true)
                .build();

        assertEquals("builderToken", response.getToken(), "Token should be set via builder");
        assertTrue(response.isAuthenticated(), "Authenticated should be set via builder");
    }

    @Test
    public void testToString() {
        // Kiểm tra phương thức toString
        AuthenticationResponse response = new AuthenticationResponse("sampleToken", true);
        String expected = "AuthenticationResponse(token=sampleToken, authenticated=true)";
        assertEquals(expected, response.toString(), "toString() should return the correct string representation");
    }

    @Test
    public void testEqualsAndHashCode() {
        // Kiểm tra equals và hashCode
        AuthenticationResponse response1 = new AuthenticationResponse("sampleToken", true);
        AuthenticationResponse response2 = new AuthenticationResponse("sampleToken", true);
        AuthenticationResponse response3 = new AuthenticationResponse("anotherToken", false);

        assertEquals(response1, response2, "Objects with the same fields should be equal");
        assertNotEquals(response1, response3, "Objects with different fields should not be equal");

        assertEquals(response1.hashCode(), response2.hashCode(), "Hash codes should be the same for equal objects");
        assertNotEquals(response1.hashCode(), response3.hashCode(), "Hash codes should be different for non-equal objects");
    }

}
