package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.entity.Category;
import cayxanh.GreencareTest.service.CategoryService;
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
    public void testGetAllCategories() {
        List<Category> categories = Arrays.asList(new Category(), new Category());
        when(categoryService.getAllCategories()).thenReturn(categories);

        ResponseEntity<List<Category>> response = categoryController.getAllCategories();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categories, response.getBody());
    }

    @Test
    public void testGetCategoryById() {
        Category category = new Category();
        when(categoryService.getCategory(1)).thenReturn(category);

        ResponseEntity<Category> response = categoryController.getCategoryById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(category, response.getBody());
    }

    @Test
    public void testFindByName() {
        Category category = new Category();
        when(categoryService.findByName("TestCategory")).thenReturn(category);

        ResponseEntity<Category> response = categoryController.findByName("TestCategory");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(category, response.getBody());
    }

    @Test
    public void testCreateCategory() {
        Category category = new Category();
        when(categoryService.createcategory(category)).thenReturn(category);

        ResponseEntity<Category> response = categoryController.createcategory(category);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(category, response.getBody());
    }

    @Test
    public void testUpdateCategory() {
        Category category = new Category();
        category.setCategoryid(1);
        when(categoryService.updateCategory(1)).thenReturn(category);

        ResponseEntity<Category> response = categoryController.updateCategory(1, category);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(category, response.getBody());
    }

    @Test
    public void testDeleteCategory() {
        when(categoryService.deleteCategory(1)).thenReturn(true);

        ResponseEntity<Void> response = categoryController.deleteCategory(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(categoryService, times(1)).deleteCategory(1);
    }

    @Test
    public void testDeleteCategoryNotFound() {
        when(categoryService.deleteCategory(1)).thenReturn(false);

        ResponseEntity<Void> response = categoryController.deleteCategory(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(categoryService, times(1)).deleteCategory(1);
    }
}
