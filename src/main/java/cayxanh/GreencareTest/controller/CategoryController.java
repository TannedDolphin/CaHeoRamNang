package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.entity.Category;
import cayxanh.GreencareTest.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // Lấy danh sách tất cả các danh mục
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // Lấy danh mục theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable int id) {
        Category category = categoryService.getCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    // Tìm danh mục theo tên
    @GetMapping("/search")
    public ResponseEntity<Category> findByName(@RequestParam String name) {
        Category category = categoryService.findByName(name);
        return category != null ?
                new ResponseEntity<>(category, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Tạo mới một danh mục
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category newCategory = categoryService.createcategory(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    // Cập nhật thông tin danh mục
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category category) {
        category.setCategoryid(id); // Đảm bảo ID từ URL được set vào đối tượng category
        Category updatedCategory = categoryService.updateCategory(id);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    // Xóa một danh mục
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        boolean isDeleted = categoryService.deleteCategory(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
