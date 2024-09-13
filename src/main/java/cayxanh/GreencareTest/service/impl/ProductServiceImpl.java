package cayxanh.GreencareTest.service.impl;

import cayxanh.GreencareTest.dto.request.CreateProductRequest;
import cayxanh.GreencareTest.entity.Category;
import cayxanh.GreencareTest.entity.Product;
import cayxanh.GreencareTest.exception.NotFoundException;
import cayxanh.GreencareTest.repo.CategoryRepository;
import cayxanh.GreencareTest.repo.ImageRepository;
import cayxanh.GreencareTest.repo.ProductRepository;
import cayxanh.GreencareTest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import cayxanh.GreencareTest.entity.Image;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Product> getList() {
        // TODO Auto-generated method stub
        return productRepository.findAll(Sort.by("id").descending());
    }

    @Override
    public Product getProduct(long id) {
        // TODO Auto-generated method stub
        Product product= productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Product With Id: " + id));

        return product;
    }


    @Override
    public Product createProduct(CreateProductRequest request) {
        // TODO Auto-generated method stub
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()-> new NotFoundException("Not Found Category With Id: " + request.getCategoryId()));
        product.setCategory(category);

        Set<Image> images = new HashSet<>();
        for(long imageId: request.getImageIds()){
            Image image = imageRepository.findById(imageId).orElseThrow(() -> new NotFoundException("Not Found Image With Id: " + imageId));
            images.add(image);
        }
        product.setImages(images);
        productRepository.save(product);
        return product;
    }

    @Override
    public Product updateProduct(long id, CreateProductRequest request) {
        // TODO Auto-generated method stub
        Product product= productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Product With Id: " + id));
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()-> new NotFoundException("Not Found Category With Id: " + request.getCategoryId()));
        product.setCategory(category);

        Set<Image> images = new HashSet<>();
        for(long imageId: request.getImageIds()){
            Image image = imageRepository.findById(imageId).orElseThrow(() -> new NotFoundException("Not Found Image With Id: " + imageId));
            images.add(image);
        }
        product.setImages(images);
        productRepository.save(product);

        return product;
    }

    @Override
    public void deleteProduct(long id) {
        // TODO Auto-generated method stub
        Product product= productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Product With Id: " + id));
        product.getImages().remove(this);
        productRepository.delete(product);
    }

    @Override
    public List<Product> getListProductByCategory(long id){
        List<Product> list =productRepository.getListProductByCategory(id);
        return list;
    }

    @Override
    public List<Product> getListByPriceRange(long id,int min, int max){
        List<Product> list =productRepository.getListProductByPriceRange(id, min, max);
        return list;
    }
}
