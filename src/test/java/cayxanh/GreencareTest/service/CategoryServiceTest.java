package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.CreateCategoryRequest;
import cayxanh.GreencareTest.entity.Category;
import cayxanh.GreencareTest.repo.CategoryRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    @Mock
    private CategoryRepo categoryRepo;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCategory() {
        CreateCategoryRequest request = new CreateCategoryRequest();
        request.setCategoryname("Electronics");

        Category category = new Category();
        category.setCategoryname(request.getCategoryname());

        when(categoryRepo.save(any(Category.class))).thenReturn(category);

        Category createdCategory = categoryService.createCategory(request);

        assertNotNull(createdCategory);
        assertEquals("Electronics", createdCategory.getCategoryname());
    }

    @Test
    public void testUpdateCategory_Success() {
        Integer categoryId = 1;
        CreateCategoryRequest request = new CreateCategoryRequest();
        request.setCategoryname("Updated Category");

        Category existingCategory = new Category();
        existingCategory.setCategoryid(categoryId);
        existingCategory.setCategoryname("Old Category");

        Category updatedCategory = new Category();
        updatedCategory.setCategoryid(categoryId);
        updatedCategory.setCategoryname(request.getCategoryname());

        when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(existingCategory));
        when(categoryRepo.save(any(Category.class))).thenReturn(updatedCategory);

        Category result = categoryService.updateCategory(categoryId, request);

        assertNotNull(result);
        assertEquals("Updated Category", result.getCategoryname());
    }

    @Test
    public void testUpdateCategory_Failure() {
        Integer categoryId = 1;
        CreateCategoryRequest request = new CreateCategoryRequest();
        request.setCategoryname("Updated Category");

        when(categoryRepo.findById(categoryId)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            categoryService.updateCategory(categoryId, request);
        });
        assertEquals("Category không tồn tại với ID: " + categoryId, thrown.getMessage());
    }

    @Test
    public void testDeleteCategory_Success() {
        Integer categoryId = 1;

        when(categoryRepo.existsById(categoryId)).thenReturn(true);

        categoryService.deleteCategory(categoryId);

        verify(categoryRepo, times(1)).deleteById(categoryId);
    }

    @Test
    public void testDeleteCategory_Failure() {
        Integer categoryId = 1;

        when(categoryRepo.existsById(categoryId)).thenReturn(false);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            categoryService.deleteCategory(categoryId);
        });
        assertEquals("Category không tồn tại với ID: " + categoryId, thrown.getMessage());
    }

    @Test
    public void testGetAllCategories() {
        Category category1 = new Category();
        category1.setCategoryname("Electronics");

        Category category2 = new Category();
        category2.setCategoryname("Books");

        when(categoryRepo.findAll()).thenReturn(Arrays.asList(category1, category2));

        List<Category> categories = categoryService.getAllCategories();

        assertNotNull(categories);
        assertEquals(2, categories.size());
        assertEquals("Electronics", categories.get(0).getCategoryname());
        assertEquals("Books", categories.get(1).getCategoryname());
    }

    @Test
    public void testGetCategoryById_Success() {
        Integer categoryId = 1;

        Category category = new Category();
        category.setCategoryid(categoryId);
        category.setCategoryname("Electronics");

        when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(category));

        Category result = categoryService.getCategoryById(categoryId);

        assertNotNull(result);
        assertEquals(categoryId, result.getCategoryid());
        assertEquals("Electronics", result.getCategoryname());
    }

    @Test
    public void testGetCategoryById_Failure() {
        Integer categoryId = 1;

        when(categoryRepo.findById(categoryId)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            categoryService.getCategoryById(categoryId);
        });
        assertEquals("Category không tồn tại với ID: " + categoryId, thrown.getMessage());
    }

    @Test
    public void testGetCategoryByName_Success() {
        String categoryName = "Electronics";

        Category category = new Category();
        category.setCategoryname(categoryName);

        when(categoryRepo.findByName(categoryName)).thenReturn(Optional.of(category));

        Category result = categoryService.getCategoryByName(categoryName);

        assertNotNull(result);
        assertEquals(categoryName, result.getCategoryname());
    }

    @Test
    public void testGetCategoryByName_Failure() {
        String categoryName = "Electronics";

        when(categoryRepo.findByName(categoryName)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            categoryService.getCategoryByName(categoryName);
        });
        assertEquals("Category không tồn tại với tên: " + categoryName, thrown.getMessage());
    }
}
