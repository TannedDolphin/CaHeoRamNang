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
public class CreateCategoryRequestTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testCategoryNameNotNullOrEmpty() {
        CreateCategoryRequest request = new CreateCategoryRequest(null);
        Set<ConstraintViolation<CreateCategoryRequest>> violations = validator.validate(request);
        violations.forEach(v -> System.out.println("Violation: " + v.getMessage()));
        assertEquals(2, violations.size()); // @NotNull và @NotEmpty đều kích hoạt

        request.setCategoryname("");
        violations = validator.validate(request);
        violations.forEach(v -> System.out.println("Violation: " + v.getMessage()));
        assertEquals(2, violations.size()); // Chỉ @NotEmpty kích hoạt
    }

    @Test
    public void testCategoryNameSize() {
        CreateCategoryRequest request = new CreateCategoryRequest("abcd");
        Set<ConstraintViolation<CreateCategoryRequest>> violations = validator.validate(request);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Độ dài danh mục từ 5-50 ký tự")));

        request.setCategoryname("a".repeat(51));
        violations = validator.validate(request);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Độ dài danh mục từ 5-50 ký tự")));
    }

    @Test
    public void testValidCategoryName() {
        CreateCategoryRequest request = new CreateCategoryRequest("ValidCategoryName");
        Set<ConstraintViolation<CreateCategoryRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }
}
