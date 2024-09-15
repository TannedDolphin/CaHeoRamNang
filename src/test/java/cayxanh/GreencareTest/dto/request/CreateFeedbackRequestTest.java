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
public class CreateFeedbackRequestTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testFeedbackNotBlank() {
        CreateFeedbackRequest request = new CreateFeedbackRequest();
        request.setFeedback("");
        request.setUserid("validUserId");

        Set<ConstraintViolation<CreateFeedbackRequest>> violations = validator.validate(request);
        violations.forEach(v -> System.out.println("Violation: " + v.getMessage()));
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Nội dung phản hồi không được để trống")));
    }

    @Test
    public void testUserIdNotNull() {
        CreateFeedbackRequest request = new CreateFeedbackRequest();
        request.setFeedback("Valid feedback");
        request.setUserid(null);
        Set<ConstraintViolation<CreateFeedbackRequest>> violations = validator.validate(request);
        violations.forEach(v -> System.out.println("Violation: " + v.getMessage()));
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("User ID không được để trống")));
    }

    @Test
    public void testValidCreateFeedbackRequest() {
        CreateFeedbackRequest request = new CreateFeedbackRequest();
        request.setFeedback("Valid feedback");
        request.setUserid("validUserId");
        Set<ConstraintViolation<CreateFeedbackRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }
}
