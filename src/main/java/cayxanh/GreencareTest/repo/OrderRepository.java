package cayxanh.GreencareTest.repo;

import cayxanh.GreencareTest.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {

    @Query(value ="Select * from Orders where user_id = :id order by id desc",nativeQuery = true)
    List<Orders> getOrderByUser(String id);
}
