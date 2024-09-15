package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.dto.request.CreateOrderRequest;
import cayxanh.GreencareTest.entity.Orders;
import cayxanh.GreencareTest.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrder() {
        CreateOrderRequest request = new CreateOrderRequest();
        Orders order = new Orders();
        when(orderService.createOrder(any(CreateOrderRequest.class))).thenReturn(order);

        ResponseEntity<Orders> response = orderController.createOrder(request);

        assertEquals(order, response.getBody());
        verify(orderService, times(1)).createOrder(any(CreateOrderRequest.class));
    }

    @Test
    public void testGetAllOrders() {
        List<Orders> orders = Arrays.asList(new Orders(), new Orders());
        when(orderService.getAllOrders()).thenReturn(orders);

        ResponseEntity<List<Orders>> response = orderController.getAllOrders();

        assertEquals(orders, response.getBody());
        verify(orderService, times(1)).getAllOrders();
    }
}
