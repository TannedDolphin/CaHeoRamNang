package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.entity.Product;
import cayxanh.GreencareTest.repo.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepo productRepo;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductid(1);
        product.setProductname("Test Product");
        product.setProductprice(100.0);
        product.setProductdescription("Test Description");
        product.setStockquantity(10);
        product.setProductimage("test_image.jpg");
    }

    @Test
    void testGetProducts() {
        when(productRepo.findAll()).thenReturn(Arrays.asList(product));

        List<Product> productsList = productService.getProducts();
        assertFalse(productsList.isEmpty());
        assertEquals(1, productsList.size());
        verify(productRepo, times(1)).findAll();
    }

    @Test
    void testGetProduct() {
        when(productRepo.findById(1)).thenReturn(Optional.of(product));

        Product foundProduct = productService.getProduct(1);
        assertNotNull(foundProduct);
        verify(productRepo, times(1)).findById(1);
    }

    @Test
    void testFindByName() {
        when(productRepo.findByName("Test Product")).thenReturn(Optional.of(product));

        Product foundProduct = productService.findByName("Test Product");
        assertNotNull(foundProduct);
        assertEquals("Test Product", foundProduct.getProductname());
        verify(productRepo, times(1)).findByName("Test Product");
    }

    @Test
    void testCreateProduct() {
        // Giả lập rằng sản phẩm không tồn tại trong kho
        when(productRepo.findByName("Test Product")).thenReturn(Optional.empty());
        // Giả lập lưu sản phẩm và trả về sản phẩm đã lưu
        when(productRepo.save(any(Product.class))).thenReturn(product);

        // Gọi phương thức createProduct
        Product createdProduct = productService.createProduct(product);

        // Kiểm tra kết quả
        assertNotNull(createdProduct);
        assertEquals("Test Product", createdProduct.getProductname());
        verify(productRepo, times(1)).findByName("Test Product");
        verify(productRepo, times(1)).save(product);
    }


    @Test
    void testUpdateProduct() {
        when(productRepo.findById(product.getProductid())).thenReturn(Optional.of(product));
        when(productRepo.save(any(Product.class))).thenReturn(product);

        Product updatedProduct = productService.updateProduct(product);
        assertNotNull(updatedProduct);
        assertEquals("Test Product", updatedProduct.getProductname());
        verify(productRepo, times(1)).findById(product.getProductid());
        verify(productRepo, times(1)).save(product);
    }

    @Test
    void testDeleteProduct() {
        when(productRepo.findById(1)).thenReturn(Optional.of(product));

        boolean isDeleted = productService.deleteProduct(1);
        assertTrue(isDeleted);
        verify(productRepo, times(1)).findById(1);
        verify(productRepo, times(1)).deleteById(1);
    }
}
