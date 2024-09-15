package cayxanh.GreencareTest.repo;

import cayxanh.GreencareTest.entity.Orders;
import cayxanh.GreencareTest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer> {

}
