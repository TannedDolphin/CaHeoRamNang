package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.CreateOrderRequest;
import cayxanh.GreencareTest.dto.request.CreateOrderItemRequest;
import cayxanh.GreencareTest.entity.OrderItem;
import cayxanh.GreencareTest.entity.Orders;
import cayxanh.GreencareTest.entity.User;
import cayxanh.GreencareTest.repo.OrderRepo;
import cayxanh.GreencareTest.repo.OrderItemRepo; // Assuming you have a repo for OrderItem
import cayxanh.GreencareTest.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderItemRepo orderItemRepo; // Assuming you have a repo for OrderItem

    @Autowired
    private UserRepo userRepo;

    // Thêm mới đơn hàng
    public Orders createOrder(CreateOrderRequest request) {
        // Kiểm tra xem người dùng có tồn tại không
        Optional<User> userOptional = userRepo.findById(request.getUserid());
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User không tồn tại với ID: " + request.getUserid());
        }

        // Tạo đơn hàng
        Orders order = new Orders();
        order.setTotalprice(request.getTotalprice());
        order.setOrderstatus(request.getOrderstatus());
        order.setUserorder(userOptional.get());

        // Lưu các item trong đơn hàng
        List<OrderItem> orderItems = request.getOrderitems().stream().map(itemRequest -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setName(itemRequest.getName());
            orderItem.setPrice(itemRequest.getPrice());
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setSubTotal(itemRequest.getSubTotal());
            orderItem.setOrder(order);
            return orderItem;
        }).toList();

        order.setOrderitems(orderItems);
        orderItemRepo.saveAll(orderItems); // Save order items first
        return orderRepo.save(order);
    }

    // Sửa đơn hàng theo ID
    public Orders updateOrder(Integer id, CreateOrderRequest request) {
        Optional<Orders> orderOptional = orderRepo.findById(id);

        if (orderOptional.isPresent()) {
            Orders order = orderOptional.get();
            // Cập nhật thông tin đơn hàng
            order.setTotalprice(request.getTotalprice());
            order.setOrderstatus(request.getOrderstatus());

            // Lưu các item mới trong đơn hàng
            List<OrderItem> orderItems = request.getOrderitems().stream().map(itemRequest -> {
                OrderItem orderItem = new OrderItem();
                orderItem.setName(itemRequest.getName());
                orderItem.setPrice(itemRequest.getPrice());
                orderItem.setQuantity(itemRequest.getQuantity());
                orderItem.setSubTotal(itemRequest.getSubTotal());
                orderItem.setOrder(order);
                return orderItem;
            }).toList();

            order.setOrderitems(orderItems);
            orderItemRepo.saveAll(orderItems); // Save updated order items
            return orderRepo.save(order);
        } else {
            throw new RuntimeException("Order không tồn tại với ID: " + id);
        }
    }

    // Xóa đơn hàng theo ID
    public void deleteOrder(Integer id) {
        if (orderRepo.existsById(id)) {
            orderRepo.deleteById(id);
        } else {
            throw new RuntimeException("Order không tồn tại với ID: " + id);
        }
    }

    // Lấy danh sách tất cả đơn hàng
    public List<Orders> getAllOrders() {
        return orderRepo.findAll();
    }

    // Tìm đơn hàng theo ID
    public Orders getOrderById(Integer id) {
        return orderRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Order không tồn tại với ID: " + id));
    }

}
