package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.entity.Orders;
import cayxanh.GreencareTest.entity.User;
import cayxanh.GreencareTest.entity.OrderItem;
import cayxanh.GreencareTest.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @Test
    public void testAddOrder() {
        // Arrange
        Orders orders = new Orders();
        orders.setOrderid(1);
        orders.setTotalprice(100.0);
        orders.setOrderstatus("Đã đặt hàng");
        User user = new User();
        user.setUserid("1");
        orders.setUserorder(user);
        OrderItem orderItem = new OrderItem();
        orderItem.setOrders(orders);
        orders.setOrderitems(List.of(orderItem));
        Orders newOrders = new Orders();
        when(orderService.addOrder(any(Orders.class))).thenReturn(newOrders);

        // Act
        ResponseEntity<Orders> response = orderController.addOrder(orders);

        // Assert
        assertEquals(ResponseEntity.ok(newOrders), response);
    }

    @Test
    public void testUpdateOrder() {
        // Arrange
        Orders orders = new Orders();
        orders.setOrderid(1);
        orders.setTotalprice(100.0);
        orders.setOrderstatus("Đã đặt hàng");
        User user = new User();
        user.setUserid("1");
        orders.setUserorder(user);
        OrderItem orderItem = new OrderItem();
        orderItem.setOrders(orders);
        orders.setOrderitems(List.of(orderItem));
        Orders updatedOrders = new Orders();
        when(orderService.updateOrder(any(Orders.class))).thenReturn(updatedOrders);

        // Act
        ResponseEntity<Orders> response = orderController.updateOrder(orders);

        // Assert
        assertEquals(ResponseEntity.ok(updatedOrders), response);
    }

    @Test
    public void testGetAllOrders() {
        // Arrange
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(new Orders());
        when(orderService.getOrders()).thenReturn(ordersList);

        // Act
        ResponseEntity<List<Orders>> response = orderController.getAllOrders();

        // Assert
        assertEquals(ResponseEntity.ok(ordersList), response);
    }

    @Test
    public void testGetOrderById() {
        // Arrange
        int id = 1;
        Orders orders = new Orders();
        orders.setOrderid(id);
        when(orderService.getOrder(id)).thenReturn(orders);

        // Act
        ResponseEntity<Orders> response = orderController.getOrderById(id);

        // Assert
        assertEquals(ResponseEntity.ok(orders), response);
    }

    @Test
    public void testDeleteOrder() {
        // Arrange
        int id = 1;
        when(orderService.deleteOrder(id)).thenReturn(null);

        // Act
        ResponseEntity<Void> response = orderController.deleteOrder(id);

        // Assert
        assertEquals(ResponseEntity.noContent().build(), response);
    }
}