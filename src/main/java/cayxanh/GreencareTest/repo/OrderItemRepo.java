package cayxanh.GreencareTest.repo;

import cayxanh.GreencareTest.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, Integer>
{
}
