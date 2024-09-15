package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.dto.request.CreateProductRequest;
import cayxanh.GreencareTest.entity.Product;
import cayxanh.GreencareTest.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProduct() {
        CreateProductRequest request = new CreateProductRequest();
        Product product = new Product();
        when(productService.createProduct(any(CreateProductRequest.class))).thenReturn(product);

        ResponseEntity<Product> response = productController.createProduct(request);

        assertEquals(product, response.getBody());
        verify(productService, times(1)).createProduct(any(CreateProductRequest.class));
    }

    @Test
    public void testUpdateProduct() {
        CreateProductRequest request = new CreateProductRequest();
        Product product = new Product();
        when(productService.updateProduct(anyInt(), any(CreateProductRequest.class))).thenReturn(product);

        ResponseEntity<Product> response = productController.updateProduct(1, request);

        assertEquals(product, response.getBody());
        verify(productService, times(1)).updateProduct(anyInt(), any(CreateProductRequest.class));
    }

    @Test
    public void testDeleteProduct() {
        doNothing().when(productService).deleteProduct(anyInt());

        ResponseEntity<String> response = productController.deleteProduct(1);

        assertEquals("Xóa thành công sản phẩm với ID: 1", response.getBody());
        verify(productService, times(1)).deleteProduct(anyInt());
    }

    @Test
    public void testGetAllProducts() {
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productService.getAllProducts()).thenReturn(products);

        ResponseEntity<List<Product>> response = productController.getAllProducts();

        assertEquals(products, response.getBody());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    public void testGetProductById() {
        Product product = new Product();
        when(productService.getProductById(anyInt())).thenReturn(product);

        ResponseEntity<Product> response = productController.getProductById(1);

        assertEquals(product, response.getBody());
        verify(productService, times(1)).getProductById(anyInt());
    }

    @Test
    public void testGetProductByName() {
        Product product = new Product();
        when(productService.getProductByName(anyString())).thenReturn(product);

        ResponseEntity<Product> response = productController.getProductByName("Test");

        assertEquals(product, response.getBody());
        verify(productService, times(1)).getProductByName(anyString());
    }
}
