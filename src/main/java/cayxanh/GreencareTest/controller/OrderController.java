package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.entity.Orders;
import cayxanh.GreencareTest.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Orders> addOrder(@RequestBody Orders orders) {
        Orders newOrders = orderService.addOrder(orders);
        return ResponseEntity.ok(newOrders);
    }

    @PutMapping
    public ResponseEntity<Orders> updateOrder(@RequestBody Orders orders) {
        Orders updatedOrders = orderService.updateOrder(orders);
        return ResponseEntity.ok(updatedOrders);
    }

    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable int id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
