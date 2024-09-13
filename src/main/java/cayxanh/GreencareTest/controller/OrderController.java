package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.dto.request.CreateOrderRequest;
import cayxanh.GreencareTest.dto.response.MessageResponse;
import cayxanh.GreencareTest.entity.Orders;
import cayxanh.GreencareTest.service.OrderService;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*",maxAge = 3600)
public class OrderController {
    @Autowired
    private OrderService orderService;


    @GetMapping("/")
    public ResponseEntity<List<Orders>> getList(){
        List<Orders> list = orderService.getList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Orders>> getListByUser(@RequestParam("username") String username){
        List<Orders> list = orderService.getOrderByUser(username);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/create")
    public ResponseEntity<?> placeOrder(@RequestBody CreateOrderRequest request){

        orderService.placeOrder(request);

        return ResponseEntity.ok(new MessageResponse("Order Placed Successfully!"));
    }
}
