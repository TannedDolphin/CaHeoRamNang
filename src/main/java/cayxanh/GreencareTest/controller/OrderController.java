package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.dto.request.CreateOrderRequest;
import cayxanh.GreencareTest.entity.Orders;
import cayxanh.GreencareTest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Thêm mới đơn hàng
    @PostMapping("/create")
    public ResponseEntity<Orders> createOrder(@RequestBody CreateOrderRequest request) {
        Orders order = orderService.createOrder(request);
        return ResponseEntity.ok(order);
    }

    // Sửa đơn hàng theo ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Integer id, @RequestBody CreateOrderRequest request) {
        Orders updatedOrder = orderService.updateOrder(id, request);
        return ResponseEntity.ok(updatedOrder);
    }

    // Xóa đơn hàng theo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Xóa thành công đơn hàng với ID: " + id);
    }

    // Lấy danh sách tất cả đơn hàng
    @GetMapping("/all")
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // Tìm đơn hàng theo ID
    @GetMapping("/findById/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Integer id) {
        Orders order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }
}
