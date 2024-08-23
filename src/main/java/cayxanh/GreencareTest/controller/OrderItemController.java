package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.entity.OrderItem;
import cayxanh.GreencareTest.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderitems")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService orderItemService;

    @PostMapping("/create")
    public ResponseEntity<OrderItem> createOrderItem(@RequestParam int id, @RequestParam int quantity) {
        OrderItem newOrderItem = orderItemService.createOrderItem(id, quantity);
        return ResponseEntity.ok(newOrderItem);
    }

    @GetMapping
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        return ResponseEntity.ok(orderItemService.getAllOrderItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable int id) {
        return ResponseEntity.ok(orderItemService.getOrderItemById(id));
    }

    @PutMapping
    public ResponseEntity<OrderItem> updateOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem updatedOrderItem = orderItemService.updateOrderItem(orderItem);
        return ResponseEntity.ok(updatedOrderItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable int id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
}
