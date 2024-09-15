package cayxanh.GreencareTest.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CreateReviewRequestTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidCreateReviewRequest() {
        CreateReviewRequest request = new CreateReviewRequest();
        request.setReviewrating(4);
        request.setReviewtext("Sản phẩm rất tốt, sẽ mua lần nữa!");
        request.setProductid(1);
        request.setUserid("user123");

        Set<ConstraintViolation<CreateReviewRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty(), "Valid object should not have violations");
    }

    @Test
    public void testInvalidReviewRatingNull() {
        CreateReviewRequest request = new CreateReviewRequest();
        request.setReviewrating(null);  // Rating is null
        request.setReviewtext("Sản phẩm khá ổn");
        request.setProductid(1);
        request.setUserid("user123");

        Set<ConstraintViolation<CreateReviewRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Rating is null, should have violations");
        assertEquals("Rating không được để trống", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidReviewRatingTooLow() {
        CreateReviewRequest request = new CreateReviewRequest();
        request.setReviewrating(0);  // Rating is too low
        request.setReviewtext("Sản phẩm chưa đạt yêu cầu");
        request.setProductid(1);
        request.setUserid("user123");

        Set<ConstraintViolation<CreateReviewRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Rating is too low, should have violations");
        assertEquals("Rating tối thiểu là 1", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidReviewRatingTooHigh() {
        CreateReviewRequest request = new CreateReviewRequest();
        request.setReviewrating(6);  // Rating is too high
        request.setReviewtext("Sản phẩm tuyệt vời");
        request.setProductid(1);
        request.setUserid("user123");

        Set<ConstraintViolation<CreateReviewRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Rating is too high, should have violations");
        assertEquals("Rating tối đa là 5", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidReviewTextBlank() {
        CreateReviewRequest request = new CreateReviewRequest();
        request.setReviewrating(4);
        request.setReviewtext("");  // Review text is blank
        request.setProductid(1);
        request.setUserid("user123");

        Set<ConstraintViolation<CreateReviewRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Review text is blank, should have violations");
        assertEquals("Nội dung đánh giá không được để trống", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidProductIdNull() {
        CreateReviewRequest request = new CreateReviewRequest();
        request.setReviewrating(4);
        request.setReviewtext("Sản phẩm rất tốt");
        request.setProductid(null);  // Product ID is null
        request.setUserid("user123");

        Set<ConstraintViolation<CreateReviewRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Product ID is null, should have violations");
        assertEquals("Product ID không được để trống", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidUserIdNull() {
        CreateReviewRequest request = new CreateReviewRequest();
        request.setReviewrating(4);
        request.setReviewtext("Sản phẩm rất tốt");
        request.setProductid(1);
        request.setUserid(null);  // User ID is null

        Set<ConstraintViolation<CreateReviewRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "User ID is null, should have violations");
        assertEquals("User ID không được để trống", violations.iterator().next().getMessage());
    }
}
