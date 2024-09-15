package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.CreateOrderRequest;
import cayxanh.GreencareTest.dto.request.CreateOrderItemRequest;
import cayxanh.GreencareTest.entity.OrderItem;
import cayxanh.GreencareTest.entity.Orders;
import cayxanh.GreencareTest.entity.User;
import cayxanh.GreencareTest.repo.OrderRepo;
import cayxanh.GreencareTest.repo.OrderItemRepo;
import cayxanh.GreencareTest.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderItemRepo orderItemRepo;

    @Autowired
    private UserRepo userRepo;

    // Thêm mới đơn hàng
    public Orders createOrder(CreateOrderRequest request) {

        // Tìm user theo username
        User user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Not Found User With Username:" + request.getUsername()));

        // Tạo đơn hàng mới
        Orders order = new Orders();
        order.setFullname(request.getFullname());
        order.setAddress(request.getAddress());
        order.setEmail(request.getEmail());
        order.setPhone(request.getPhone());
        order.setOrderstatus(request.getOrderstatus());
        order.setUserorder(user);  // Liên kết user với đơn hàng

        // Tạo danh sách các item
        List<OrderItem> orderItemsList = new ArrayList<>();
        long totalPrice = 0;

        for (CreateOrderItemRequest rq : request.getOrderitems()) {
            OrderItem orderDetail = new OrderItem();
            orderDetail.setName(rq.getName());
            orderDetail.setPrice(rq.getPrice());
            orderDetail.setQuantity(rq.getQuantity());
            orderDetail.setSubTotal(rq.getPrice() * rq.getQuantity());

            // Liên kết OrderItem với Order
            orderDetail.setOrder(order);
            orderItemsList.add(orderDetail);  // Thêm vào danh sách OrderItems

            totalPrice += orderDetail.getSubTotal();
        }

        // Lưu đơn hàng trước
        order.setTotalprice(totalPrice);
        order.setOrderitems(orderItemsList);  // Liên kết danh sách OrderItems với Order

        // Lưu đơn hàng và các item
        orderRepo.save(order);
        return order;
    }

    // Lấy danh sách tất cả đơn hàng
    public List<Orders> getAllOrders() {
        return orderRepo.findAll();
    }
}
