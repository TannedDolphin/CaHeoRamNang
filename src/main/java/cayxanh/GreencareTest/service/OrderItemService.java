package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.CreateOrderItemRequest;
import cayxanh.GreencareTest.entity.OrderItem;
import cayxanh.GreencareTest.entity.Orders;
import cayxanh.GreencareTest.repo.OrderItemRepo;
import cayxanh.GreencareTest.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepo orderItemRepo;

    @Autowired
    private OrderRepo orderRepo; // Giả định bạn có repo cho Orders

    public OrderItem createOrderItem(CreateOrderItemRequest request) {
        OrderItem orderItem = new OrderItem();
        orderItem.setName(request.getName());
        orderItem.setPrice(request.getPrice());
        orderItem.setQuantity(request.getQuantity());
        orderItem.setSubTotal(request.getSubTotal());

        Orders order = orderRepo.findById((int) request.getOrderid())
                .orElseThrow(() -> new RuntimeException("Order không tồn tại với ID: " + request.getOrderid()));
        orderItem.setOrder(order);

        return orderItemRepo.save(orderItem);
    }

    public OrderItem updateOrderItem(int id, CreateOrderItemRequest request) {
        OrderItem orderItem = orderItemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem không tồn tại với ID: " + id));

        orderItem.setName(request.getName());
        orderItem.setPrice(request.getPrice());
        orderItem.setQuantity(request.getQuantity());
        orderItem.setSubTotal(request.getSubTotal());

        Orders order = orderRepo.findById((int) request.getOrderid())
                .orElseThrow(() -> new RuntimeException("Order không tồn tại với ID: " + request.getOrderid()));
        orderItem.setOrder(order);

        return orderItemRepo.save(orderItem);
    }

    public void deleteOrderItem(int id) {
        if (!orderItemRepo.existsById(id)) {
            throw new RuntimeException("OrderItem không tồn tại với ID: " + id);
        }
        orderItemRepo.deleteById(id);
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepo.findAll();
    }

    public OrderItem getOrderItemById(int id) {
        return orderItemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem không tồn tại với ID: " + id));
    }

}
