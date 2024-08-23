package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.entity.OrderItem;
import cayxanh.GreencareTest.repo.OrderItemRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderItemService {
    OrderItemRepo orderItemRepo;
    public OrderItem createOrderItem(OrderItem orderItem) {
        OrderItem newOrderItem = orderItemRepo.save(orderItem);
        if (newOrderItem != null) {
            return newOrderItem;
        }
        throw new RuntimeException("Order item could not be created");
    }
    public List<OrderItem> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepo.findAll();
        if (orderItems == null) {
            throw new RuntimeException("Order items could not be found");
        }
        return orderItems;
    }
    public OrderItem getOrderItemById(int id) {
        return orderItemRepo.findById(id).orElseThrow(() -> new RuntimeException("Order item could not be found"));
    }
    public OrderItem updateOrderItem(OrderItem orderItem) {
        OrderItem updatedOrderItem = orderItemRepo.findById(orderItem.getOrderitemid()).orElseThrow(() -> new RuntimeException("Order item could not be found"));
        updatedOrderItem.setQuantity(orderItem.getQuantity());
        OrderItem updatedOrderItem1 = orderItemRepo.save(updatedOrderItem);
        return updatedOrderItem1;
    }
    public boolean deleteOrderItem(int id) {
        OrderItem findedOrderItem = orderItemRepo.findById(id).orElseThrow(() -> new RuntimeException("Order item could not be found"));
        orderItemRepo.deleteById(findedOrderItem.getOrderitemid());
        return true;
    }
}
