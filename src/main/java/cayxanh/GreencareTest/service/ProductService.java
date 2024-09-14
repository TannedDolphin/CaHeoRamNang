package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.CreateProductRequest;
import cayxanh.GreencareTest.entity.Category;
import cayxanh.GreencareTest.entity.Image;
import cayxanh.GreencareTest.entity.Product;
import cayxanh.GreencareTest.repo.CategoryRepo;
import cayxanh.GreencareTest.repo.ImageRepo;
import cayxanh.GreencareTest.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ImageRepo imageRepo;

    // Thêm mới sản phẩm
    public Product createProduct(CreateProductRequest request) {
        Category category = categoryRepo.findById(request.getCategoryid())
                .orElseThrow(() -> new RuntimeException("Category không tồn tại với ID: " + request.getCategoryid()));

        // Tạo mới sản phẩm
        Product product = new Product();
        product.setProductname(request.getProductname());
        product.setProductprice(request.getProductprice());
        product.setProductdescription(request.getProductdescription());
        product.setStockquantity(request.getStockquantity());
        product.setCategoryproduct(category);

        // Thêm ảnh vào sản phẩm
        Set<Image> images = request.getImageIds().stream()
                .map(imageId -> imageRepo.findById(imageId)
                        .orElseThrow(() -> new RuntimeException("Image không tồn tại với ID: " + imageId)))
                .collect(Collectors.toSet());
        product.setImages(images);

        // Lưu sản phẩm
        return productRepo.save(product);
    }

    // Sửa sản phẩm theo ID
    public Product updateProduct(int id, CreateProductRequest request) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product không tồn tại với ID: " + id));

        Category category = categoryRepo.findById(request.getCategoryid())
                .orElseThrow(() -> new RuntimeException("Category không tồn tại với ID: " + request.getCategoryid()));

        // Cập nhật thông tin sản phẩm
        product.setProductname(request.getProductname());
        product.setProductprice(request.getProductprice());
        product.setProductdescription(request.getProductdescription());
        product.setStockquantity(request.getStockquantity());
        product.setCategoryproduct(category);

        // Cập nhật ảnh của sản phẩm
        Set<Image> images = request.getImageIds().stream()
                .map(imageId -> imageRepo.findById(imageId)
                        .orElseThrow(() -> new RuntimeException("Image không tồn tại với ID: " + imageId)))
                .collect(Collectors.toSet());
        product.setImages(images);

        return productRepo.save(product);
    }

    // Xóa sản phẩm theo ID
    public void deleteProduct(int id) {
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
    public Product getProductById(int id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product không tồn tại với ID: " + id));
    }

    // Tìm sản phẩm theo tên
    public Product getProductByName(String name) {
        return productRepo.findByName(name)
                .orElseThrow(() -> new RuntimeException("Product không tồn tại với tên: " + name));
    }
}
