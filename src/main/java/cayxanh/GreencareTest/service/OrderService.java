package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.entity.Order;
import cayxanh.GreencareTest.entity.OrderItem;
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
        Order newOrder = orderRepo.save(order);
        if (newOrder == null) {
            throw new RuntimeException("Order is empty");
        }
        for(OrderItem orderItem : order.getOrderitems()){
            OrderItem newOrderitem = orderItemService.createOrderItem(orderItem);
        }
        return newOrder;
    }
    public Order updateOrder(Order order) {
        Order updatedOrder = orderRepo.findById(order.getOrderid()).orElseThrow(() -> new RuntimeException("Order not found"));
        updatedOrder.setTotalprice(order.getTotalprice());
        updatedOrder.setOrderstatus(order.getOrderstatus());
        Order updateOrder = orderRepo.save(updatedOrder);
        return updateOrder;
    }
    public Order deleteOrder(int id) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepo.deleteById(order.getOrderid());
        return order;
    }
}
