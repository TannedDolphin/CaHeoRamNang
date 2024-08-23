package cayxanh.GreencareTest.repo;

import cayxanh.GreencareTest.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
    @Query("select p from Category p where p.categoryname=:input")
    Optional<Category> findByName(String input);
}
