package cayxanh.GreencareTest.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserCreationRequestTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testUserCreationRequestBuilder() {
        UserCreationRequest request = UserCreationRequest.builder()
                .username("user123")
                .password("password123")
                .email("user@example.com")
                .fullname("John Doe")
                .phone("1234567890")
                .build();

        assertNotNull(request);
        assertEquals("user123", request.getUsername());
        assertEquals("password123", request.getPassword());
        assertEquals("user@example.com", request.getEmail());
        assertEquals("John Doe", request.getFullname());
        assertEquals("1234567890", request.getPhone());
    }

    @Test
    void testUserCreationRequestNoArgsConstructor() {
        UserCreationRequest request = new UserCreationRequest();

        assertNotNull(request);
        assertNull(request.getUsername());
        assertNull(request.getPassword());
        assertNull(request.getEmail());
        assertNull(request.getFullname());
        assertNull(request.getPhone());
    }

    @Test
    void testUserCreationRequestAllArgsConstructor() {
        UserCreationRequest request = new UserCreationRequest("user123", "password123", "user@example.com", "John Doe", "1234567890");

        assertNotNull(request);
        assertEquals("user123", request.getUsername());
        assertEquals("password123", request.getPassword());
        assertEquals("user@example.com", request.getEmail());
        assertEquals("John Doe", request.getFullname());
        assertEquals("1234567890", request.getPhone());
    }

    @Test
    void testUserCreationRequestSettersAndGetters() {
        UserCreationRequest request = new UserCreationRequest();
        request.setUsername("user123");
        request.setPassword("password123");
        request.setEmail("user@example.com");
        request.setFullname("John Doe");
        request.setPhone("1234567890");

        assertEquals("user123", request.getUsername());
        assertEquals("password123", request.getPassword());
        assertEquals("user@example.com", request.getEmail());
        assertEquals("John Doe", request.getFullname());
        assertEquals("1234567890", request.getPhone());
    }

    @Test
    void testUserCreationRequestValidation() {
        UserCreationRequest request = new UserCreationRequest("us", "pass", "invalid-email", "", "1234567890");

        Set<ConstraintViolation<UserCreationRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals(4, violations.size());
    }
}
