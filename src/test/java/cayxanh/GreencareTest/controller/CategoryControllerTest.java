package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.dto.request.CreateCategoryRequest;
import cayxanh.GreencareTest.entity.Category;
import cayxanh.GreencareTest.service.CategoryService;
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
import static org.mockito.Mockito.*;

public class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCategory() {
        CreateCategoryRequest request = new CreateCategoryRequest();
        Category category = new Category();
        when(categoryService.createCategory(any(CreateCategoryRequest.class))).thenReturn(category);

        ResponseEntity<Category> response = categoryController.createCategory(request);

        assertEquals(category, response.getBody());
        verify(categoryService, times(1)).createCategory(any(CreateCategoryRequest.class));
    }

    @Test
    public void testUpdateCategory() {
        CreateCategoryRequest request = new CreateCategoryRequest();
        Category category = new Category();
        when(categoryService.updateCategory(anyInt(), any(CreateCategoryRequest.class))).thenReturn(category);

        ResponseEntity<Category> response = categoryController.updateCategory(1, request);

        assertEquals(category, response.getBody());
        verify(categoryService, times(1)).updateCategory(anyInt(), any(CreateCategoryRequest.class));
    }

    @Test
    public void testDeleteCategory() {
        doNothing().when(categoryService).deleteCategory(anyInt());

        ResponseEntity<String> response = categoryController.deleteCategory(1);

        assertEquals("Xóa thành công danh mục với ID: 1", response.getBody());
        verify(categoryService, times(1)).deleteCategory(anyInt());
    }

    @Test
    public void testGetAllCategories() {
        List<Category> categories = Arrays.asList(new Category(), new Category());
        when(categoryService.getAllCategories()).thenReturn(categories);

        ResponseEntity<List<Category>> response = categoryController.getAllCategories();

        assertEquals(categories, response.getBody());
        verify(categoryService, times(1)).getAllCategories();
    }

    @Test
    public void testGetCategoryById() {
        Category category = new Category();
        when(categoryService.getCategoryById(anyInt())).thenReturn(category);

        ResponseEntity<Category> response = categoryController.getCategoryById(1);

        assertEquals(category, response.getBody());
        verify(categoryService, times(1)).getCategoryById(anyInt());
    }

    @Test
    public void testGetCategoryByName() {
        Category category = new Category();
        when(categoryService.getCategoryByName(anyString())).thenReturn(category);

        ResponseEntity<Category> response = categoryController.getCategoryByName("Test");

        assertEquals(category, response.getBody());
        verify(categoryService, times(1)).getCategoryByName(anyString());
    }
}
