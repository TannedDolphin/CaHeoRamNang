package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.CreateProductRequest;
import cayxanh.GreencareTest.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getList();

    List<Product> getListProductByCategory(long id);

    List<Product> getListByPriceRange(long id,int min, int max);

    Product getProduct(long id);

    Product createProduct(CreateProductRequest request);

    Product updateProduct(long id, CreateProductRequest request);

    void deleteProduct(long id);

}

