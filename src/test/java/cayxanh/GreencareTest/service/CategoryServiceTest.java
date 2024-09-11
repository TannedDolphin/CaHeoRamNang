package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.entity.Category;
import cayxanh.GreencareTest.repo.CategoryRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    @Mock
    private CategoryRepo categoryRepo;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        category = new Category();
        category.setCategoryid(1);
        category.setCategoryname("Test Category");
    }

    @Test
    void testGetCategory() {
        when(categoryRepo.findById(1)).thenReturn(Optional.of(category));
        Category foundCategory = categoryService.getCategory(1);
        assertNotNull(foundCategory);
        assertEquals("Test Category", foundCategory.getCategoryname());
    }

    @Test
    void testGetAllCategories() {
        when(categoryRepo.findAll()).thenReturn(Arrays.asList(category));
        List<Category> categories = categoryService.getAllCategories();
        assertFalse(categories.isEmpty());
        assertEquals(1, categories.size());
    }

    @Test
    void testFindByName() {
        when(categoryRepo.findByName("Test Category")).thenReturn(Optional.of(category));
        Category foundCategory = categoryService.findByName("Test Category");
        assertNotNull(foundCategory);
        assertEquals("Test Category", foundCategory.getCategoryname());
    }

    @Test
    void testCreateCategory() {
        when(categoryRepo.findByName("Test Category")).thenReturn(Optional.empty());
        when(categoryRepo.save(category)).thenReturn(category);

        Category createdCategory = categoryService.createcategory(category);

        assertNotNull(createdCategory);
        assertEquals("Test Category", createdCategory.getCategoryname());
    }

    @Test
    void testUpdateCategory() {
        Category updatedCategory = new Category();
        updatedCategory.setCategoryname("Updated Category");

        when(categoryRepo.findById(1)).thenReturn(Optional.of(category));
        when(categoryRepo.save(category)).thenReturn(category);

    }

    @Test
    void testDeleteCategory() {
        when(categoryRepo.findById(1)).thenReturn(Optional.of(category));
        doNothing().when(categoryRepo).deleteById(1);
        boolean isDeleted = categoryService.deleteCategory(1);
        assertTrue(isDeleted);
        verify(categoryRepo, times(1)).deleteById(1);
    }
}
