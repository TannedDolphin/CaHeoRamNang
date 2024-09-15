package cayxanh.GreencareTest.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CreateProductRequestTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidCreateProductRequest() {
        Set<Long> imageIds = new HashSet<>();
        imageIds.add(1L);
        imageIds.add(2L);

        CreateProductRequest request = new CreateProductRequest();
        request.setProductname("Sản phẩm A");
        request.setProductprice(100000);
        request.setProductdescription("Mô tả sản phẩm A");
        request.setStockquantity(50);
        request.setCategoryid(1);
        request.setImageIds(imageIds);

        Set<ConstraintViolation<CreateProductRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty(), "Valid object should not have violations");
    }

    @Test
    public void testInvalidProductNameBlank() {
        CreateProductRequest request = new CreateProductRequest();
        request.setProductname("");  // Product name is blank
        request.setProductprice(100000);
        request.setProductdescription("Mô tả sản phẩm B");
        request.setStockquantity(50);
        request.setCategoryid(1);
        request.setImageIds(Set.of(1L));

        Set<ConstraintViolation<CreateProductRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Product name is blank, should have violations");
        assertEquals("Tên sản phẩm không được để trống", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidProductPriceNegative() {
        CreateProductRequest request = new CreateProductRequest();
        request.setProductname("Sản phẩm B");
        request.setProductprice(-5000);  // Product price is negative
        request.setProductdescription("Mô tả sản phẩm B");
        request.setStockquantity(50);
        request.setCategoryid(1);
        request.setImageIds(Set.of(1L));

        Set<ConstraintViolation<CreateProductRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Product price is negative, should have violations");
        assertEquals("Giá sản phẩm phải lớn hơn hoặc bằng 0", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidStockQuantityNegative() {
        CreateProductRequest request = new CreateProductRequest();
        request.setProductname("Sản phẩm C");
        request.setProductprice(50000);
        request.setProductdescription("Mô tả sản phẩm C");
        request.setStockquantity(-10);  // Stock quantity is negative
        request.setCategoryid(1);
        request.setImageIds(Set.of(1L));

        Set<ConstraintViolation<CreateProductRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Stock quantity is negative, should have violations");
        assertEquals("Số lượng tồn kho phải lớn hơn hoặc bằng 0", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidCategoryIdNull() {
        CreateProductRequest request = new CreateProductRequest();
        request.setProductname("Sản phẩm D");
        request.setProductprice(50000);
        request.setProductdescription("Mô tả sản phẩm D");
        request.setStockquantity(50);
        request.setCategoryid(null);  // Category ID is null
        request.setImageIds(Set.of(1L));

        Set<ConstraintViolation<CreateProductRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Category ID is null, should have violations");
        assertEquals("Category ID không được để trống", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidImageIdsNull() {
        CreateProductRequest request = new CreateProductRequest();
        request.setProductname("Sản phẩm E");
        request.setProductprice(50000);
        request.setProductdescription("Mô tả sản phẩm E");
        request.setStockquantity(50);
        request.setCategoryid(1);
        request.setImageIds(null);  // Image IDs are null

        Set<ConstraintViolation<CreateProductRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Image IDs are null, should have violations");
        assertEquals("Ảnh sản phẩm rỗng", violations.iterator().next().getMessage());
    }
}
