package cayxanh.GreencareTest.repo;

import cayxanh.GreencareTest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value ="Select * from Product where category_id = :id",nativeQuery = true)
    List<Product> getListProductByCategory(long id);

    @Query(value = "Select * from Product where category_id = :id and price between :min and :max",nativeQuery = true)
    List<Product> getListProductByPriceRange(long id,int min,int max);

}
