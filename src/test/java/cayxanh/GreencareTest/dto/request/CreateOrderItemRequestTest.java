package cayxanh.GreencareTest.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CreateOrderItemRequestTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidCreateOrderItemRequest() {
        CreateOrderItemRequest request = new CreateOrderItemRequest();
        request.setName("Sản phẩm A");
        request.setPrice(10000);
        request.setQuantity(5);

        Set<ConstraintViolation<CreateOrderItemRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty(), "Valid object should not have violations");
    }

    @Test
    public void testInvalidNameNull() {
        CreateOrderItemRequest request = new CreateOrderItemRequest();
        request.setName(null);
        request.setPrice(10000);
        request.setQuantity(5);

        Set<ConstraintViolation<CreateOrderItemRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Name is null, should have violations");
        assertEquals("Tên sản phẩm rỗng", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidNameTooShort() {
        CreateOrderItemRequest request = new CreateOrderItemRequest();
        request.setName("abc");
        request.setPrice(10000);
        request.setQuantity(5);

        Set<ConstraintViolation<CreateOrderItemRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Name is too short, should have violations");
        assertEquals("Tên sản phẩm từ 5-50 ký tự", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidPriceNegative() {
        CreateOrderItemRequest request = new CreateOrderItemRequest();
        request.setName("Sản phẩm B");
        request.setPrice(-5000);
        request.setQuantity(5);

        Set<ConstraintViolation<CreateOrderItemRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Price is negative, should have violations");
        assertEquals("Giá sản phẩm từ 0 trở lên", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidQuantityZero() {
        CreateOrderItemRequest request = new CreateOrderItemRequest();
        request.setName("Sản phẩm C");
        request.setPrice(5000);
        request.setQuantity(0);

        Set<ConstraintViolation<CreateOrderItemRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Quantity is 0, should have violations");
        assertEquals("Số lượng sản phẩm từ 1 trở lên", violations.iterator().next().getMessage());
    }
}
