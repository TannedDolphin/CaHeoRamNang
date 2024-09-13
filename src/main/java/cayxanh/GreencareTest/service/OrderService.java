package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.CreateOrderRequest;
import cayxanh.GreencareTest.entity.Orders;

import java.util.List;

public interface OrderService {

    void placeOrder(CreateOrderRequest request);

    List<Orders> getList();

    List<Orders> getOrderByUser(String username);
}
