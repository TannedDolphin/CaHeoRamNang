package cayxanh.GreencareTest.dto.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntrospectResponseTest {

    @Test
    public void testNoArgsConstructor() {
        // Kiểm tra constructor không có tham số
        IntrospectResponse response = new IntrospectResponse();
        assertFalse(response.isValid(), "Valid should be false after no-args constructor");
    }

    @Test
    public void testAllArgsConstructor() {
        // Kiểm tra constructor với tất cả các tham số
        IntrospectResponse response = new IntrospectResponse(true);
        assertTrue(response.isValid(), "Valid should be set via all-args constructor");
    }

    @Test
    public void testSettersAndGetters() {
        // Kiểm tra setter và getter
        IntrospectResponse response = new IntrospectResponse();
        response.setValid(false);

        assertFalse(response.isValid(), "Valid should be set and retrieved correctly");
    }

    @Test
    public void testBuilderPattern() {
        // Kiểm tra builder pattern
        IntrospectResponse response = IntrospectResponse.builder()
                .valid(true)
                .build();

        assertTrue(response.isValid(), "Valid should be set via builder");
    }

    @Test
    public void testToString() {
        // Kiểm tra phương thức toString
        IntrospectResponse response = new IntrospectResponse(true);
        String expected = "IntrospectResponse(valid=true)";
        assertEquals(expected, response.toString(), "toString() should return the correct string representation");
    }

    @Test
    public void testEqualsAndHashCode() {
        // Kiểm tra equals và hashCode
        IntrospectResponse response1 = new IntrospectResponse(true);
        IntrospectResponse response2 = new IntrospectResponse(true);
        IntrospectResponse response3 = new IntrospectResponse(false);

        assertEquals(response1, response2, "Objects with the same fields should be equal");
        assertNotEquals(response1, response3, "Objects with different fields should not be equal");

        assertEquals(response1.hashCode(), response2.hashCode(), "Hash codes should be the same for equal objects");
        assertNotEquals(response1.hashCode(), response3.hashCode(), "Hash codes should be different for non-equal objects");
    }
}
