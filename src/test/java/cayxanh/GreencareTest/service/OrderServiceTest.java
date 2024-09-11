package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.entity.Orders;
import cayxanh.GreencareTest.entity.OrderItem;
import cayxanh.GreencareTest.entity.Product;
import cayxanh.GreencareTest.repo.OrderRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepo orderRepo;

    @InjectMocks
    private OrderService orderService;

    private Orders order;
    private OrderItem orderItem;
    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductprice(100.0);

        orderItem = new OrderItem();
        orderItem.setOrderitemproduct(product);
        orderItem.setQuantity(2);

        order = new Orders();
        order.setOrderitems(Arrays.asList(orderItem));
        order.setOrderid(1);
    }

    @Test
    void testGetOrders() {
        when(orderRepo.findAll()).thenReturn(Arrays.asList(order));

        List<Orders> ordersList = orderService.getOrders();
        assertFalse(ordersList.isEmpty());
        assertEquals(1, ordersList.size());
        verify(orderRepo, times(1)).findAll();
    }

    @Test
    void testGetOrder() {
        when(orderRepo.findById(1)).thenReturn(Optional.of(order));

        Orders foundOrder = orderService.getOrder(1);
        assertNotNull(foundOrder);
        verify(orderRepo, times(1)).findById(1);
    }

    @Test
    void testAddOrder() {
        when(orderRepo.save(any(Orders.class))).thenReturn(order);

        Orders savedOrder = orderService.addOrder(order);
        assertNotNull(savedOrder);
        assertEquals(200.0, savedOrder.getTotalprice());
        verify(orderRepo, times(1)).save(order);
    }

    @Test
    void testUpdateOrder() {
        when(orderRepo.findById(order.getOrderid())).thenReturn(Optional.of(order));
        when(orderRepo.save(any(Orders.class))).thenReturn(order);

        Orders updatedOrder = orderService.updateOrder(order);
        assertNotNull(updatedOrder);
        assertEquals(200.0, updatedOrder.getTotalprice());
        verify(orderRepo, times(1)).findById(order.getOrderid());
        verify(orderRepo, times(1)).save(order);
    }

    @Test
    void testDeleteOrder() {
        when(orderRepo.findById(1)).thenReturn(Optional.of(order));

        Orders deletedOrder = orderService.deleteOrder(1);
        assertNotNull(deletedOrder);
        verify(orderRepo, times(1)).findById(1);
        verify(orderRepo, times(1)).deleteById(1);
    }
}
