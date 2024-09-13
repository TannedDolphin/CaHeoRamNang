package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.CreateCategoryRequest;
import cayxanh.GreencareTest.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    List<Category> getListEnabled();

    Category createCategory(CreateCategoryRequest request);

    Category updateCategory(long id,CreateCategoryRequest request);

    void enableCategory(long id);

    void deleteCategory(long id);
}
