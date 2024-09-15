package cayxanh.GreencareTest.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserUpdateRequestTest {

    @Test
    public void testNoArgsConstructor() {
        // Kiểm tra constructor không có tham số
        UserUpdateRequest request = new UserUpdateRequest();
        assertNull(request.getPassword(), "Password should be null after no-args constructor");
        assertNull(request.getEmail(), "Email should be null after no-args constructor");
        assertNull(request.getFullname(), "Fullname should be null after no-args constructor");
        assertNull(request.getPhone(), "Phone should be null after no-args constructor");
    }

    @Test
    public void testAllArgsConstructor() {
        // Kiểm tra constructor có tất cả các tham số
        UserUpdateRequest request = new UserUpdateRequest(
                "newPassword123",
                "newemail@example.com",
                "New Fullname",
                "0123456789"
        );
        assertEquals("newPassword123", request.getPassword(), "Password should be set via all-args constructor");
        assertEquals("newemail@example.com", request.getEmail(), "Email should be set via all-args constructor");
        assertEquals("New Fullname", request.getFullname(), "Fullname should be set via all-args constructor");
        assertEquals("0123456789", request.getPhone(), "Phone should be set via all-args constructor");
    }

    @Test
    public void testSettersAndGetters() {
        // Kiểm tra setter và getter
        UserUpdateRequest request = new UserUpdateRequest();
        request.setPassword("password123");
        request.setEmail("user@example.com");
        request.setFullname("John Doe");
        request.setPhone("0987654321");

        assertEquals("password123", request.getPassword(), "Password should be set and retrieved correctly");
        assertEquals("user@example.com", request.getEmail(), "Email should be set and retrieved correctly");
        assertEquals("John Doe", request.getFullname(), "Fullname should be set and retrieved correctly");
        assertEquals("0987654321", request.getPhone(), "Phone should be set and retrieved correctly");
    }

    @Test
    public void testBuilderPattern() {
        // Kiểm tra builder pattern
        UserUpdateRequest request = UserUpdateRequest.builder()
                .password("builderPassword123")
                .email("builder@example.com")
                .fullname("Builder Fullname")
                .phone("0123456789")
                .build();

        assertEquals("builderPassword123", request.getPassword(), "Password should be set via builder");
        assertEquals("builder@example.com", request.getEmail(), "Email should be set via builder");
        assertEquals("Builder Fullname", request.getFullname(), "Fullname should be set via builder");
        assertEquals("0123456789", request.getPhone(), "Phone should be set via builder");
    }

    @Test
    public void testToString() {
        // Kiểm tra phương thức toString
        UserUpdateRequest request = new UserUpdateRequest("password123", "user@example.com", "John Doe", "0987654321");
        String expected = "UserUpdateRequest(password=password123, email=user@example.com, fullname=John Doe, phone=0987654321)";
        assertEquals(expected, request.toString(), "toString() should return the correct string representation");
    }

    @Test
    public void testEqualsAndHashCode() {
        // Kiểm tra equals và hashCode
        UserUpdateRequest request1 = new UserUpdateRequest("password123", "user@example.com", "John Doe", "0987654321");
        UserUpdateRequest request2 = new UserUpdateRequest("password123", "user@example.com", "John Doe", "0987654321");
        UserUpdateRequest request3 = new UserUpdateRequest("password456", "anotheruser@example.com", "Jane Doe", "0123456789");

        assertEquals(request1, request2, "Objects with the same fields should be equal");
        assertNotEquals(request1, request3, "Objects with different fields should not be equal");

        assertEquals(request1.hashCode(), request2.hashCode(), "Hash codes should be the same for equal objects");
        assertNotEquals(request1.hashCode(), request3.hashCode(), "Hash codes should be different for non-equal objects");
    }
}
