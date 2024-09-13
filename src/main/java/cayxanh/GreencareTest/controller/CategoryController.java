package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.dto.request.CreateCategoryRequest;
import cayxanh.GreencareTest.dto.response.MessageResponse;
import cayxanh.GreencareTest.entity.Category;
import cayxanh.GreencareTest.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*",maxAge = 3600)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<?> getListCategory(){
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/enabled")
    public ResponseEntity<List<Category>> getListEnabled(){
        List<Category> categories = categoryService.getListEnabled();
        return ResponseEntity.ok(categories);
    }


    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CreateCategoryRequest request){
        Category category = categoryService.createCategory(request);

        return ResponseEntity.ok(category);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable long id, @Valid @RequestBody CreateCategoryRequest request){
        Category category = categoryService.updateCategory(id, request);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/enable/{id}")
    public ResponseEntity<?> enabled(@PathVariable long id){
        categoryService.enableCategory(id);
        return ResponseEntity.ok(new MessageResponse("Cập nhật thành công"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(new MessageResponse("Xóa thành công"));
    }


}
