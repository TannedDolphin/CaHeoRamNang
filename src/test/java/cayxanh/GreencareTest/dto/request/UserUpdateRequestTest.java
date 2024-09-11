package cayxanh.GreencareTest.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserUpdateRequestTest {

    @Test
    void testUserUpdateRequestBuilder() {
        UserUpdateRequest request = UserUpdateRequest.builder()
                .password("newpassword")
                .email("newemail@example.com")
                .fullname("New Name")
                .phone("0987654321")
                .build();

        assertNotNull(request);
        assertEquals("newpassword", request.getPassword());
        assertEquals("newemail@example.com", request.getEmail());
        assertEquals("New Name", request.getFullname());
        assertEquals("0987654321", request.getPhone());
    }

    @Test
    void testUserUpdateRequestNoArgsConstructor() {
        UserUpdateRequest request = new UserUpdateRequest();

        assertNotNull(request);
        assertNull(request.getPassword());
        assertNull(request.getEmail());
        assertNull(request.getFullname());
        assertNull(request.getPhone());
    }

    @Test
    void testUserUpdateRequestAllArgsConstructor() {
        UserUpdateRequest request = new UserUpdateRequest("newpassword", "newemail@example.com", "New Name", "0987654321");

        assertNotNull(request);
        assertEquals("newpassword", request.getPassword());
        assertEquals("newemail@example.com", request.getEmail());
        assertEquals("New Name", request.getFullname());
        assertEquals("0987654321", request.getPhone());
    }

    @Test
    void testUserUpdateRequestSettersAndGetters() {
        UserUpdateRequest request = new UserUpdateRequest();
        request.setPassword("newpassword");
        request.setEmail("newemail@example.com");
        request.setFullname("New Name");
        request.setPhone("0987654321");

        assertEquals("newpassword", request.getPassword());
        assertEquals("newemail@example.com", request.getEmail());
        assertEquals("New Name", request.getFullname());
        assertEquals("0987654321", request.getPhone());
    }
}
