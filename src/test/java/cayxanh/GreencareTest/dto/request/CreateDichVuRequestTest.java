package cayxanh.GreencareTest.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
public class CreateDichVuRequestTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testDichVuNameNotNullOrEmpty() {
        CreateDichVuRequest request = new CreateDichVuRequest(null, "Valid description");
        Set<ConstraintViolation<CreateDichVuRequest>> violations = validator.validate(request);
        violations.forEach(v -> System.out.println("Violation: " + v.getMessage()));
        assertEquals(2, violations.size()); // @NotNull và @NotEmpty đều kích hoạt

        request.setDichvuname("");
        violations = validator.validate(request);
        violations.forEach(v -> System.out.println("Violation: " + v.getMessage()));
        assertEquals(2, violations.size()); // Chỉ @NotEmpty kích hoạt
    }

    @Test
    public void testDichVuDescriptionNotNullOrEmpty() {
        CreateDichVuRequest request = new CreateDichVuRequest("Valid name", null);
        Set<ConstraintViolation<CreateDichVuRequest>> violations = validator.validate(request);
        violations.forEach(v -> System.out.println("Violation: " + v.getMessage()));
        assertEquals(2, violations.size()); // @NotNull và @NotEmpty đều kích hoạt

        request.setDichvudescription("");
        violations = validator.validate(request);
        violations.forEach(v -> System.out.println("Violation: " + v.getMessage()));
        assertEquals(2, violations.size()); // Chỉ @NotEmpty kích hoạt
    }

    @Test
    public void testDichVuNameSize() {
        CreateDichVuRequest request = new CreateDichVuRequest("abcd", "Valid description");
        Set<ConstraintViolation<CreateDichVuRequest>> violations = validator.validate(request);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Độ dài tiêu đề từ 1-300 ký tự")));

        request.setDichvuname("a".repeat(301));
        violations = validator.validate(request);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Độ dài tiêu đề từ 1-300 ký tự")));
    }

    @Test
    public void testDichVuDescriptionSize() {
        CreateDichVuRequest request = new CreateDichVuRequest("Valid name", "abcd");
        Set<ConstraintViolation<CreateDichVuRequest>> violations = validator.validate(request);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Độ dài mô tả từ 1-300 ký tự")));

        request.setDichvudescription("a".repeat(3001));
        violations = validator.validate(request);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Độ dài mô tả từ 1-300 ký tự")));
    }

    @Test
    public void testValidCreateDichVuRequest() {
        CreateDichVuRequest request = new CreateDichVuRequest("Valid name", "Valid description");
        Set<ConstraintViolation<CreateDichVuRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }
}
