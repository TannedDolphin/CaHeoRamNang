package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.entity.Product;
import cayxanh.GreencareTest.repo.ProductRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
    ProductRepo productRepo;
    public List<Product> getProducts() {
        List<Product> products = productRepo.findAll();
        if (products.isEmpty()) {
            throw new RuntimeException("empty products list");
        }
        return products;
    }
    public Product getProduct(int id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("product not found"));
        return product;
    }
    public Product findByName(String name) {
        Optional<Product> product = productRepo.findByName(name);
        return product.orElse(null); // Trả về null nếu không tìm thấy
    }
    public Product createProduct(Product product) {
        Product existProduct = productRepo.findByName(product.getProductname())
                .orElseThrow(() -> new RuntimeException("product not found"));
        if (existProduct != null) {
            throw new RuntimeException("product already exists");
        }
        return productRepo.save(product);
    }
    public Product updateProduct(Product product) {
        Product updatedProduct = productRepo.findById(product.getProductid()).orElseThrow(() -> new RuntimeException("product not found"));
        updatedProduct.setProductname(product.getProductname());
        updatedProduct.setProductdescription(product.getProductdescription());
        updatedProduct.setProductprice(product.getProductprice());
        updatedProduct.setProductimage(product.getProductimage());
        updatedProduct.setStockquantity(product.getStockquantity());
        Product updatedProduct1 = productRepo.save(updatedProduct);
        return updatedProduct1;
    }
    public boolean deleteProduct(int id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("product not found"));
        productRepo.deleteById(product.getProductid());
        return true;
    }
}

