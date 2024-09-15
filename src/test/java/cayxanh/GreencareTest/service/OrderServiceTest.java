package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.CreateOrderItemRequest;
import cayxanh.GreencareTest.dto.request.CreateOrderRequest;
import cayxanh.GreencareTest.entity.OrderItem;
import cayxanh.GreencareTest.entity.Orders;
import cayxanh.GreencareTest.entity.User;
import cayxanh.GreencareTest.repo.OrderItemRepo;
import cayxanh.GreencareTest.repo.OrderRepo;
import cayxanh.GreencareTest.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private OrderRepo orderRepo;

    @Mock
    private OrderItemRepo orderItemRepo;

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrder() {
        // Prepare mock data
        CreateOrderRequest request = new CreateOrderRequest();
        request.setUsername("john_doe");
        request.setFullname("John Doe");
        request.setAddress("123 Main St");
        request.setEmail("john.doe@example.com");
        request.setPhone("555-1234");
        request.setOrderstatus("PENDING");

        CreateOrderItemRequest itemRequest1 = new CreateOrderItemRequest();
        itemRequest1.setName("Product A");
        itemRequest1.setPrice(100L); // Thay đổi từ 100.00 thành 100L
        itemRequest1.setQuantity(2);

        CreateOrderItemRequest itemRequest2 = new CreateOrderItemRequest();
        itemRequest2.setName("Product B");
        itemRequest2.setPrice(50L); // Thay đổi từ 50.00 thành 50L
        itemRequest2.setQuantity(1);

        request.setOrderitems(Arrays.asList(itemRequest1, itemRequest2));

        User user = new User(); // Assuming User class has required fields set up
        user.setUsername("john_doe");

        when(userRepo.findByUsername("john_doe")).thenReturn(Optional.of(user));

        Orders order = new Orders();
        order.setFullname("John Doe");
        order.setAddress("123 Main St");
        order.setEmail("john.doe@example.com");
        order.setPhone("555-1234");
        order.setOrderstatus("PENDING");
        order.setTotalprice(250L); // Thay đổi từ 250.00 thành 250L

        when(orderRepo.save(any(Orders.class))).thenReturn(order);

        Orders createdOrder = orderService.createOrder(request);

        assertNotNull(createdOrder);
        assertEquals("John Doe", createdOrder.getFullname());
        assertEquals("123 Main St", createdOrder.getAddress());
        assertEquals("john.doe@example.com", createdOrder.getEmail());
        assertEquals("555-1234", createdOrder.getPhone());
        assertEquals("PENDING", createdOrder.getOrderstatus());
        assertEquals(250L, createdOrder.getTotalprice()); // Thay đổi từ 250.00 thành 250L
        assertEquals(2, createdOrder.getOrderitems().size());
    }


    @Test
    public void testGetAllOrders() {
        Orders order1 = new Orders();
        Orders order2 = new Orders();

        when(orderRepo.findAll()).thenReturn(Arrays.asList(order1, order2));

        List<Orders> orders = orderService.getAllOrders();

        assertNotNull(orders);
        assertEquals(2, orders.size());
    }
}
