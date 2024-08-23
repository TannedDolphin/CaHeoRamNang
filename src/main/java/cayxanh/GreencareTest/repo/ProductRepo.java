package cayxanh.GreencareTest.repo;

import cayxanh.GreencareTest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Query("select p from Product p where p.productname=:input")
    Optional<Product> findByName(String input);
}
