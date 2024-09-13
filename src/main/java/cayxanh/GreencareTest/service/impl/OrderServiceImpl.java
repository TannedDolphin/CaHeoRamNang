package cayxanh.GreencareTest.service.impl;

import cayxanh.GreencareTest.dto.request.CreateOrderDetailRequest;
import cayxanh.GreencareTest.dto.request.CreateOrderRequest;
import cayxanh.GreencareTest.entity.OrderDetail;
import cayxanh.GreencareTest.entity.Orders;
import cayxanh.GreencareTest.entity.User;
import cayxanh.GreencareTest.exception.NotFoundException;
import cayxanh.GreencareTest.repo.OrderDetailRepository;
import cayxanh.GreencareTest.repo.OrderRepository;
import cayxanh.GreencareTest.repo.UserRepository;
import cayxanh.GreencareTest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void placeOrder(CreateOrderRequest request) {
        // TODO Auto-generated method stub
        Orders order = new Orders();
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new NotFoundException("Not Found User With Username:" + request.getUsername()));
        order.setAddress(request.getAddress());
        order.setEmail(request.getEmail());
        order.setPhone(request.getPhone());
        order.setNote(request.getNote());
        orderRepository.save(order);
        long totalPrice = 0;
        for(CreateOrderDetailRequest rq: request.getOrderDetails()){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setName(rq.getName());
            orderDetail.setPrice(rq.getPrice());
            orderDetail.setQuantity(rq.getQuantity());
            orderDetail.setSubTotal(rq.getPrice()* rq.getQuantity());
            orderDetail.setOrder(order);
            totalPrice += orderDetail.getSubTotal();
            orderDetailRepository.save(orderDetail);

        }
        order.setTotalPrice(totalPrice);
        order.setUser(user);
        orderRepository.save(order);
    }

    @Override
    public List<Orders> getList() {
        return orderRepository.findAll(Sort.by("id").descending());
    }

    @Override
    public List<Orders> getOrderByUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Not Found User With Username:" + username));

        List<Orders> orders = orderRepository.getOrderByUser(user.getUsername());
        return orders;
    }

}
