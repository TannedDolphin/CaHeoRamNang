package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.entity.Orders;
import cayxanh.GreencareTest.entity.OrderItem;
import cayxanh.GreencareTest.entity.Product;
import cayxanh.GreencareTest.repo.OrderRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService {
    OrderRepo orderRepo;

    public List<Orders> getOrders() {
        List<Orders> orders = orderRepo.findAll();
        if (orders.isEmpty()) {
            throw new RuntimeException("Orders list is empty");
        }
        return orders;
    }
    @PreAuthorize("hasRole('ADMIN')")
    public Orders getOrder(int id) {
        Orders orders = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return orders;
    }
    public Orders addOrder(Orders orders) {
        double totalprice = 0;

        for (OrderItem orderItem : orders.getOrderitems()) {
            Product product = orderItem.getProduct(); // Lấy sản phẩm từ OrderItem
            totalprice += orderItem.getQuantity() * product.getProductprice(); // Tính tổng tiền
        }
        orders.setTotalprice(totalprice);
        return orderRepo.save(orders);
    }
    public Orders updateOrder(Orders orders) {
        Orders existingOrders = orderRepo.findById(orders.getOrderid())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        double totalprice = 0;
        for (OrderItem orderItem : orders.getOrderitems()) {
            Product product = orderItem.getProduct(); // Lấy sản phẩm từ OrderItem
            totalprice += orderItem.getQuantity() * product.getProductprice(); // Tính tổng tiền
        }
        existingOrders.setTotalprice(totalprice);
        existingOrders.setOrderstatus(orders.getOrderstatus());
        return orderRepo.save(existingOrders);
    }
    public Orders deleteOrder(int id) {
        Orders orders = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepo.deleteById(orders.getOrderid());
        return orders;
    }
}
