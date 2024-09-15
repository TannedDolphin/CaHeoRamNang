package cayxanh.GreencareTest.dto.request;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class UserCreationRequestTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidUserCreationRequest() {
        // Kiểm tra trường hợp hợp lệ
        UserCreationRequest request = UserCreationRequest.builder()
                .username("johnDoe")
                .password("securePassword123")
                .email("john.doe@example.com")
                .fullname("John Doe")
                .phone("0123456789")
                .build();

        Set<ConstraintViolation<UserCreationRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty(), "Valid object should not have violations");
    }

    @Test
    public void testUsernameTooShort() {
        // Kiểm tra khi username quá ngắn
        UserCreationRequest request = UserCreationRequest.builder()
                .username("ab")  // Không hợp lệ, nhỏ hơn 3 ký tự
                .password("securePassword123")
                .email("john.doe@example.com")
                .fullname("John Doe")
                .phone("0123456789")
                .build();

        Set<ConstraintViolation<UserCreationRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Username is too short, should have violations");
        assertEquals("username must be atleast 3 char", violations.iterator().next().getMessage());
    }

    @Test
    public void testPasswordTooShort() {
        // Kiểm tra khi password quá ngắn
        UserCreationRequest request = UserCreationRequest.builder()
                .username("johnDoe")
                .password("1234567")  // Không hợp lệ, nhỏ hơn 8 ký tự
                .email("john.doe@example.com")
                .fullname("John Doe")
                .phone("0123456789")
                .build();

        Set<ConstraintViolation<UserCreationRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Password is too short, should have violations");
        assertEquals("password must be atleast 8 char", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidEmail() {
        // Kiểm tra email không hợp lệ
        UserCreationRequest request = UserCreationRequest.builder()
                .username("johnDoe")
                .password("securePassword123")
                .email("invalid-email")  // Email không hợp lệ
                .fullname("John Doe")
                .phone("0123456789")
                .build();

        Set<ConstraintViolation<UserCreationRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Email is invalid, should have violations");
        assertEquals("must be a well-formed email address", violations.iterator().next().getMessage());
    }

    @Test
    public void testFullNameBlank() {
        // Kiểm tra khi fullname bị bỏ trống
        UserCreationRequest request = UserCreationRequest.builder()
                .username("johnDoe")
                .password("securePassword123")
                .email("john.doe@example.com")
                .fullname("")  // Fullname trống
                .phone("0123456789")
                .build();

        Set<ConstraintViolation<UserCreationRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Fullname is blank, should have violations");
        assertEquals("full name must not be blank", violations.iterator().next().getMessage());
    }

    @Test
    public void testPhoneOptional() {
        // Kiểm tra khi phone có thể không được nhập (optional)
        UserCreationRequest request = UserCreationRequest.builder()
                .username("johnDoe")
                .password("securePassword123")
                .email("john.doe@example.com")
                .fullname("John Doe")
                .phone(null)  // Phone có thể không cần giá trị
                .build();

        Set<ConstraintViolation<UserCreationRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty(), "Phone is optional, object should be valid even when phone is null");
    }

}
