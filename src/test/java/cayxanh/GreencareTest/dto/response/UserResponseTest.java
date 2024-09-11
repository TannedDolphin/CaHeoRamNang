package cayxanh.GreencareTest.dto.response;

import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class UserResponseTest {

    @Test
    void testUserResponseBuilder() {
        UserResponse response = UserResponse.builder()
                .userid("12345")
                .username("user123")
                .email("user@example.com")
                .fullname("John Doe")
                .phone("1234567890")
                .roles(Set.of("ROLE_USER", "ROLE_ADMIN"))
                .build();

        assertNotNull(response);
        assertEquals("12345", response.getUserid());
        assertEquals("user123", response.getUsername());
        assertEquals("user@example.com", response.getEmail());
        assertEquals("John Doe", response.getFullname());
        assertEquals("1234567890", response.getPhone());
        assertEquals(Set.of("ROLE_USER", "ROLE_ADMIN"), response.getRoles());
    }

    @Test
    void testUserResponseNoArgsConstructor() {
        UserResponse response = new UserResponse();

        assertNotNull(response);
        assertNull(response.getUserid());
        assertNull(response.getUsername());
        assertNull(response.getEmail());
        assertNull(response.getFullname());
        assertNull(response.getPhone());
        assertNull(response.getRoles());
    }

    @Test
    void testUserResponseAllArgsConstructor() {
        UserResponse response = new UserResponse("12345", "user123", "user@example.com", "John Doe", "1234567890", Set.of("ROLE_USER", "ROLE_ADMIN"));

        assertNotNull(response);
        assertEquals("12345", response.getUserid());
        assertEquals("user123", response.getUsername());
        assertEquals("user@example.com", response.getEmail());
        assertEquals("John Doe", response.getFullname());
        assertEquals("1234567890", response.getPhone());
        assertEquals(Set.of("ROLE_USER", "ROLE_ADMIN"), response.getRoles());
    }

    @Test
    void testUserResponseSettersAndGetters() {
        UserResponse response = new UserResponse();
        response.setUserid("12345");
        response.setUsername("user123");
        response.setEmail("user@example.com");
        response.setFullname("John Doe");
        response.setPhone("1234567890");
        response.setRoles(Set.of("ROLE_USER", "ROLE_ADMIN"));

        assertEquals("12345", response.getUserid());
        assertEquals("user123", response.getUsername());
        assertEquals("user@example.com", response.getEmail());
        assertEquals("John Doe", response.getFullname());
        assertEquals("1234567890", response.getPhone());
        assertEquals(Set.of("ROLE_USER", "ROLE_ADMIN"), response.getRoles());
    }
}
