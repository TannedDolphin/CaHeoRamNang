package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.dto.request.CreateProductRequest;
import cayxanh.GreencareTest.dto.response.MessageResponse;
import cayxanh.GreencareTest.entity.Product;
import cayxanh.GreencareTest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/")
    public ResponseEntity<List<Product>> getList(){
        List<Product> list = productService.getList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> getListProductByCategory(@PathVariable long id){
        List<Product> list =  productService.getListProductByCategory(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/range")
    public ResponseEntity<List<Product>> getListProductByPriceRange(@RequestParam("id") long id,@RequestParam("min") int min, @RequestParam("max") int max){
        List<Product> list = productService.getListByPriceRange(id, min, max);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable long id){
        Product product = productService.getProduct(id);

        return ResponseEntity.ok(product);
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request){
        Product product = productService.createProduct(request);

        return ResponseEntity.ok(product);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id,@RequestBody CreateProductRequest request){
        Product product = productService.updateProduct(id, request);

        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id){
        productService.deleteProduct(id);

        return ResponseEntity.ok(new MessageResponse("Product is d  elete"));
    }


}