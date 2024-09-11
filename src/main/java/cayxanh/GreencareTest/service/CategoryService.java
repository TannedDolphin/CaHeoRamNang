package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.entity.Category;
import cayxanh.GreencareTest.repo.CategoryRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {
    CategoryRepo categoryRepo;
    @PreAuthorize("hasRole('ADMIN')")
    public Category getCategory(int id) {
        Category category= categoryRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Category not found!!"));
        return category;
    }

    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        if (categories.isEmpty()) {
            throw new RuntimeException("No categories found");
        }
        return categories;
    }

    public Category findByName(String name) {
        Optional<Category> category = categoryRepo.findByName(name);
        return category.orElse(null); // Trả về null nếu không tìm thấy
    }
    @PreAuthorize("hasRole('ADMIN')")
    public Category createcategory(Category category) {
        Category existcategory = categoryRepo.findByName(category.getCategoryname())
                .orElseThrow(() -> new RuntimeException("category not found"));
        if (existcategory != null) {
            throw new RuntimeException("category already exists");
        }
        return categoryRepo.save(category);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public Category updateCategory(int id) {
        Category category = categoryRepo.findById(id).orElseThrow(()->new RuntimeException("Category not found"));
        updateCategory(id).getCategoryname();
        return categoryRepo.save(category);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public boolean deleteCategory(int id) {
        Category category = categoryRepo.findById(id).orElseThrow(()->new RuntimeException("Category not found"));
        categoryRepo.deleteById(category.getCategoryid());
        return true;
    }
}
