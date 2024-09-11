package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.entity.Product;
import cayxanh.GreencareTest.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void testGetAllProducts() {
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productService.getProducts()).thenReturn(products);

        ResponseEntity<List<Product>> response = productController.getAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @Test
    public void testGetProductById() {
        Product product = new Product();
        when(productService.getProduct(1)).thenReturn(product);

        ResponseEntity<Product> response = productController.getProductById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testGetProductByName() {
        Product product = new Product();
        when(productService.findByName("TestProduct")).thenReturn(product);

        ResponseEntity<Product> response = productController.getProductByName("TestProduct");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        when(productService.createProduct(product)).thenReturn(product);

        ResponseEntity<Product> response = productController.createProduct(product);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product();
        product.setProductid(1);
        when(productService.updateProduct(product)).thenReturn(product);

        ResponseEntity<Product> response = productController.updateProduct(1, product);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testDeleteProduct() {
        doAnswer(invocation -> {
            // You can add any additional logic here if needed
            return null;
        }).when(productService).deleteProduct(1);

        ResponseEntity<Void> response = productController.deleteProduct(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).deleteProduct(1);
    }
}
