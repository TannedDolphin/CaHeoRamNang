package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.CreateCategoryRequest;
import cayxanh.GreencareTest.entity.Category;
import cayxanh.GreencareTest.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    // Thêm mới category
    public Category createCategory(CreateCategoryRequest request) {
        Category category = new Category();
        category.setCategoryname(request.getCategoryname());
        return categoryRepo.save(category);
    }

    // Sửa category theo ID
    public Category updateCategory(Integer id, CreateCategoryRequest request) {
        Optional<Category> categoryOptional = categoryRepo.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setCategoryname(request.getCategoryname());
            return categoryRepo.save(category);
        } else {
            throw new RuntimeException("Category không tồn tại với ID: " + id);
        }
    }

    // Xóa category theo ID
    public void deleteCategory(Integer id) {
        if (categoryRepo.existsById(id)) {
            categoryRepo.deleteById(id);
        } else {
            throw new RuntimeException("Category không tồn tại với ID: " + id);
        }
    }

    // Lấy danh sách tất cả category
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    // Tìm category theo ID
    public Category getCategoryById(Integer id) {
        return categoryRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Category không tồn tại với ID: " + id));
    }

    // Tìm category theo tên
    public Category getCategoryByName(String name) {
        return categoryRepo.findByName(name).orElseThrow(() ->
                new RuntimeException("Category không tồn tại với tên: " + name));
    }
}
