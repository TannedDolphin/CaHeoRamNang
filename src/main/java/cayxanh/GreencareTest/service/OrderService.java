package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.entity.Order;
import cayxanh.GreencareTest.entity.OrderItem;
import cayxanh.GreencareTest.entity.Product;
import cayxanh.GreencareTest.repo.OrderRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService {
    OrderRepo orderRepo;
    OrderItemService orderItemService;
    public List<Order> getOrders() {
        List<Order> orders = orderRepo.findAll();
        if (orders.isEmpty()) {
            throw new RuntimeException("Orders list is empty");
        }
        return orders;
    }
    public Order getOrder(int id) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return order;
    }
    public Order addOrder(Order order) {
        double totalprice = 0;

        for (OrderItem orderItem : order.getOrderitems()) {
            Product product = orderItem.getProduct(); // Lấy sản phẩm từ OrderItem
            totalprice += orderItem.getQuantity() * product.getProductprice(); // Tính tổng tiền
        }
        order.setTotalprice(totalprice);
        return orderRepo.save(order);
    }
    public Order updateOrder(Order order) {
        Order existingOrder = orderRepo.findById(order.getOrderid())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        double totalprice = 0;
        for (OrderItem orderItem : order.getOrderitems()) {
            Product product = orderItem.getProduct(); // Lấy sản phẩm từ OrderItem
            totalprice += orderItem.getQuantity() * product.getProductprice(); // Tính tổng tiền
        }
        existingOrder.setTotalprice(totalprice);
        existingOrder.setOrderstatus(order.getOrderstatus());
        return orderRepo.save(existingOrder);
    }
    public Order deleteOrder(int id) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepo.deleteById(order.getOrderid());
        return order;
    }
}
