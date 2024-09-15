package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.CreateProductRequest;
import cayxanh.GreencareTest.entity.Category;
import cayxanh.GreencareTest.entity.Image;
import cayxanh.GreencareTest.entity.Product;
import cayxanh.GreencareTest.repo.CategoryRepo;
import cayxanh.GreencareTest.repo.ImageRepo;
import cayxanh.GreencareTest.repo.ProductRepo;
import cayxanh.GreencareTest.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepo productRepo;

    @Mock
    private CategoryRepo categoryRepo;

    @Mock
    private ImageRepo imageRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProduct() {
        // Mock category
        Category category = new Category();
        category.setCategoryid(1);
        when(categoryRepo.findById(anyInt())).thenReturn(Optional.of(category));

        // Mock images
        Image image1 = new Image(1L, "image1", "type1");
        Image image2 = new Image(2L, "image2", "type2");
        when(imageRepo.findById(anyLong()))
                .thenReturn(Optional.of(image1))
                .thenReturn(Optional.of(image2));

        // Create and mock Product
        Product product = new Product();
        product.setProductname("Product1");
        product.setProductprice(100.0);
        product.setProductdescription("Description");
        product.setStockquantity(10);
        product.setCategoryproduct(category);
        product.setImages(new HashSet<>(Arrays.asList(image1, image2)));

        when(productRepo.save(any(Product.class))).thenReturn(product);

        CreateProductRequest request = new CreateProductRequest();
        request.setProductname("Product1");
        request.setProductprice(100.0);
        request.setProductdescription("Description");
        request.setStockquantity(10);
        request.setCategoryid(1);
        request.setImageIds(new HashSet<>(Arrays.asList(1L, 2L))); // Set<Long>

        Product createdProduct = productService.createProduct(request);

        assertNotNull(createdProduct);
        assertEquals("Product1", createdProduct.getProductname(), "Product name should be 'Product1'");
        assertEquals(100.0, createdProduct.getProductprice(), "Product price should be 100.0");
        assertEquals("Description", createdProduct.getProductdescription(), "Product description should be 'Description'");
        assertEquals(10, createdProduct.getStockquantity(), "Product stock quantity should be 10");
        assertEquals(1, createdProduct.getCategoryproduct().getCategoryid(), "Category ID should be 1");
        assertEquals(2, createdProduct.getImages().size(), "Product should have 2 images");
    }

    @Test
    public void testUpdateProduct() {
        // Mock category
        Category category = new Category();
        category.setCategoryid(1);
        when(categoryRepo.findById(anyInt())).thenReturn(Optional.of(category));

        // Mock images
        Image image1 = new Image(1L, "image1", "type1");
        Image image2 = new Image(2L, "image2", "type2");
        when(imageRepo.findById(anyLong()))
                .thenReturn(Optional.of(image1))
                .thenReturn(Optional.of(image2));

        // Mock existing Product
        Product existingProduct = new Product();
        existingProduct.setProductid(1);
        when(productRepo.findById(anyInt())).thenReturn(Optional.of(existingProduct));

        // Mock updated Product
        Product updatedProduct = new Product();
        updatedProduct.setProductname("Updated Product");
        updatedProduct.setProductprice(150.0);
        updatedProduct.setProductdescription("Updated Description");
        updatedProduct.setStockquantity(20);
        updatedProduct.setCategoryproduct(category);
        updatedProduct.setImages(new HashSet<>(Arrays.asList(image1, image2)));

        when(productRepo.save(any(Product.class))).thenReturn(updatedProduct);

        CreateProductRequest request = new CreateProductRequest();
        request.setProductname("Updated Product");
        request.setProductprice(150.0);
        request.setProductdescription("Updated Description");
        request.setStockquantity(20);
        request.setCategoryid(1);
        request.setImageIds(new HashSet<>(Arrays.asList(1L, 2L))); // Set<Long>

        Product product = productService.updateProduct(1, request);

        assertNotNull(product);
        assertEquals("Updated Product", product.getProductname(), "Product name should be 'Updated Product'");
        assertEquals(150.0, product.getProductprice(), "Product price should be 150.0");
        assertEquals("Updated Description", product.getProductdescription(), "Product description should be 'Updated Description'");
        assertEquals(20, product.getStockquantity(), "Product stock quantity should be 20");
        assertEquals(1, product.getCategoryproduct().getCategoryid(), "Category ID should be 1");
        assertEquals(2, product.getImages().size(), "Product should have 2 images");
    }

    @Test
    public void testDeleteProduct() {
        when(productRepo.existsById(anyInt())).thenReturn(true);

        productService.deleteProduct(1);

        verify(productRepo, times(1)).deleteById(1);
    }

    @Test
    public void testGetAllProducts() {
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productRepo.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(2, result.size(), "Should return 2 products");
    }

    @Test
    public void testGetProductById() {
        Product product = new Product();
        when(productRepo.findById(anyInt())).thenReturn(Optional.of(product));

        Product result = productService.getProductById(1);

        assertNotNull(result);
    }

    @Test
    public void testGetProductByName() {
        Product product = new Product();
        when(productRepo.findByName(any(String.class))).thenReturn(Optional.of(product));

        Product result = productService.getProductByName("Product1");

        assertNotNull(result);
    }
}
