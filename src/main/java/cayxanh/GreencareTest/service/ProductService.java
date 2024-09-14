package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.CreateProductRequest;
import cayxanh.GreencareTest.entity.Category;
import cayxanh.GreencareTest.entity.Product;
import cayxanh.GreencareTest.repo.CategoryRepo;
import cayxanh.GreencareTest.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    // Thêm mới sản phẩm
    public Product createProduct(CreateProductRequest request) {
        // Kiểm tra xem category có tồn tại không
        Optional<Category> categoryOptional = categoryRepo.findById(request.getCategoryid());
        if (categoryOptional.isEmpty()) {
            throw new RuntimeException("Category không tồn tại với ID: " + request.getCategoryid());
        }

        // Tạo mới sản phẩm
        Product product = new Product();
        product.setProductname(request.getProductname());
        product.setProductprice(request.getProductprice());
        product.setProductdescription(request.getProductdescription());
        product.setStockquantity(request.getStockquantity());
        product.setProductimage(request.getProductimage());
        product.setCategoryproduct(categoryOptional.get());

        return productRepo.save(product);
    }

    // Sửa sản phẩm theo ID
    public Product updateProduct(Integer id, CreateProductRequest request) {
        Optional<Product> productOptional = productRepo.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            // Kiểm tra xem category có tồn tại không
            Optional<Category> categoryOptional = categoryRepo.findById(request.getCategoryid());
            if (categoryOptional.isEmpty()) {
                throw new RuntimeException("Category không tồn tại với ID: " + request.getCategoryid());
            }

            // Cập nhật sản phẩm
            product.setProductname(request.getProductname());
            product.setProductprice(request.getProductprice());
            product.setProductdescription(request.getProductdescription());
            product.setStockquantity(request.getStockquantity());
            product.setProductimage(request.getProductimage());
            product.setCategoryproduct(categoryOptional.get());

            return productRepo.save(product);
        } else {
            throw new RuntimeException("Product không tồn tại với ID: " + id);
        }
    }

    // Xóa sản phẩm theo ID
    public void deleteProduct(Integer id) {
        if (productRepo.existsById(id)) {
            productRepo.deleteById(id);
        } else {
            throw new RuntimeException("Product không tồn tại với ID: " + id);
        }
    }

    // Lấy danh sách tất cả sản phẩm
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    // Tìm sản phẩm theo ID
    public Product getProductById(Integer id) {
        return productRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Product không tồn tại với ID: " + id));
    }

    // Tìm sản phẩm theo tên
    public Product getProductByName(String name) {
        return productRepo.findByName(name).orElseThrow(() ->
                new RuntimeException("Product không tồn tại với tên: " + name));
    }
}
