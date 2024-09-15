package cayxanh.GreencareTest.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CreateOrderRequestTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidCreateOrderRequest() {
        CreateOrderItemRequest item = new CreateOrderItemRequest();
        item.setName("Sản phẩm A");
        item.setPrice(10000);
        item.setQuantity(2);
        item.setSubTotal(2000);
        ArrayList<CreateOrderItemRequest> items = new ArrayList<>();
        items.add(item);

        CreateOrderRequest request = new CreateOrderRequest();
        request.setFullname("Nguyễn Văn A");
        request.setAddress("123 Đường A, Quận B, TP.C");
        request.setEmail("email@example.com");
        request.setPhone("0912345678");
        request.setTotalprice(20000);
        request.setOrderstatus("pending");
        request.setUsername("nguyenvana");
        request.setOrderitems(items);

        Set<ConstraintViolation<CreateOrderRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty(), "Valid object should not have violations");
    }

    @Test
    public void testInvalidFullnameNull() {
        CreateOrderRequest request = new CreateOrderRequest();
        request.setFullname(null);
        request.setAddress("123 Đường A, Quận B, TP.C");
        request.setEmail("email@example.com");
        request.setPhone("0912345678");

        Set<ConstraintViolation<CreateOrderRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Fullname is null, should have violations");
        assertEquals("Tên khách hàng rỗng", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidFullnameTooShort() {
        CreateOrderRequest request = new CreateOrderRequest();
        request.setFullname("Ng");
        request.setAddress("123 Đường A, Quận B, TP.C");
        request.setEmail("email@example.com");
        request.setPhone("0912345678");

        Set<ConstraintViolation<CreateOrderRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Fullname is too short, should have violations");
        assertEquals(" Tên khách hàng từ 3-100 ký tự", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidEmailFormat() {
        CreateOrderRequest request = new CreateOrderRequest();
        request.setFullname("Nguyễn Văn A");
        request.setAddress("123 Đường A, Quận B, TP.C");
        request.setEmail("invalidemail");
        request.setPhone("0912345678");

        Set<ConstraintViolation<CreateOrderRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Email format is invalid, should have violations");
        assertEquals("Email không đúng định dạng", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidPhoneEmpty() {
        CreateOrderRequest request = new CreateOrderRequest();
        request.setFullname("Nguyễn Văn A");
        request.setAddress("123 Đường A, Quận B, TP.C");
        request.setEmail("email@example.com");
        request.setPhone("");

        Set<ConstraintViolation<CreateOrderRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Phone is empty, should have violations");
        assertEquals("Số điện thoại rỗng", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidAddressNull() {
        CreateOrderRequest request = new CreateOrderRequest();
        request.setFullname("Nguyễn Văn A");
        request.setAddress(null);
        request.setEmail("email@example.com");
        request.setPhone("0912345678");

        Set<ConstraintViolation<CreateOrderRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Address is null, should have violations");
        assertEquals("địa chỉ rỗng", violations.iterator().next().getMessage());
    }
}
