package cayxanh.GreencareTest.dto.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageResponseTest {

    @Test
    public void testNoArgsConstructor() {
        // Kiểm tra constructor không có tham số
        MessageResponse response = new MessageResponse();
        assertNull(response.getMessage(), "Message should be null after no-args constructor");
    }

    @Test
    public void testAllArgsConstructor() {
        // Kiểm tra constructor có tham số
        MessageResponse response = new MessageResponse("Operation successful");
        assertEquals("Operation successful", response.getMessage(), "Message should be set via all-args constructor");
    }

    @Test
    public void testSettersAndGetters() {
        // Kiểm tra setter và getter
        MessageResponse response = new MessageResponse();
        response.setMessage("Updated message");

        assertEquals("Updated message", response.getMessage(), "Message should be set and retrieved correctly");
    }

    @Test
    public void testToString() {
        // Kiểm tra phương thức toString
        MessageResponse response = new MessageResponse("Test message");
        String expected = "MessageResponse(message=Test message)";
        assertEquals(expected, response.toString(), "toString() should return the correct string representation");
    }

    @Test
    public void testEqualsAndHashCode() {
        // Kiểm tra equals và hashCode
        MessageResponse response1 = new MessageResponse("Same message");
        MessageResponse response2 = new MessageResponse("Same message");
        MessageResponse response3 = new MessageResponse("Different message");

        assertEquals(response1, response2, "Objects with the same fields should be equal");
        assertNotEquals(response1, response3, "Objects with different fields should not be equal");

        assertEquals(response1.hashCode(), response2.hashCode(), "Hash codes should be the same for equal objects");
        assertNotEquals(response1.hashCode(), response3.hashCode(), "Hash codes should be different for non-equal objects");
    }
}
