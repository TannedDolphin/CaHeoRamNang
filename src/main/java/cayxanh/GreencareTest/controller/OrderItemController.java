package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.dto.request.CreateOrderItemRequest;
import cayxanh.GreencareTest.entity.OrderItem;
import cayxanh.GreencareTest.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping("/create")
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody CreateOrderItemRequest request) {
        OrderItem orderItem = orderItemService.createOrderItem(request);
        return ResponseEntity.ok(orderItem);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable int id, @RequestBody CreateOrderItemRequest request) {
        OrderItem orderItem = orderItemService.updateOrderItem(id, request);
        return ResponseEntity.ok(orderItem);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable int id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(orderItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable int id) {
        OrderItem orderItem = orderItemService.getOrderItemById(id);
        return ResponseEntity.ok(orderItem);
    }

}
