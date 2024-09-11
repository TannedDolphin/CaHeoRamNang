package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.entity.OrderItem;
import cayxanh.GreencareTest.entity.Product;
import cayxanh.GreencareTest.repo.OrderItemRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderItemServiceTest {

    @Mock
    private OrderItemRepo orderItemRepo;

    @Mock
    private ProductService productService;

    @InjectMocks
    private OrderItemService orderItemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrderItem() {
        Product product = new Product();
        when(productService.getProduct(1)).thenReturn(product);

        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(1);
        orderItem.setQuantity(10);
        when(orderItemRepo.save(any(OrderItem.class))).thenReturn(orderItem);

        /*OrderItem result = orderItemService.createOrderItem(1, 10);

        assertNotNull(result);
        assertEquals(1, result.getProduct());
        assertEquals(10, result.getQuantity());*/
    }

    @Test
    void testGetAllOrderItems() {
        OrderItem orderItem1 = new OrderItem();
        OrderItem orderItem2 = new OrderItem();
        when(orderItemRepo.findAll()).thenReturn(Arrays.asList(orderItem1, orderItem2));

        List<OrderItem> orderItems = orderItemService.getAllOrderItems();

        assertNotNull(orderItems);
        assertEquals(2, orderItems.size());
    }

    @Test
    void testGetOrderItemById() {
        OrderItem orderItem = new OrderItem();
        when(orderItemRepo.findById(1)).thenReturn(Optional.of(orderItem));

        OrderItem result = orderItemService.getOrderItemById(1);

        assertNotNull(result);
    }

    @Test
    void testUpdateOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderitemid(1);
        orderItem.setQuantity(5);
        when(orderItemRepo.findById(1)).thenReturn(Optional.of(orderItem));
        when(orderItemRepo.save(any(OrderItem.class))).thenReturn(orderItem);

        OrderItem updatedOrderItem = new OrderItem();
        updatedOrderItem.setOrderitemid(1);
        updatedOrderItem.setQuantity(10);

        OrderItem result = orderItemService.updateOrderItem(updatedOrderItem);

        assertNotNull(result);
        assertEquals(10, result.getQuantity());
    }

    @Test
    void testDeleteOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderitemid(1);
        when(orderItemRepo.findById(1)).thenReturn(Optional.of(orderItem));

        boolean result = orderItemService.deleteOrderItem(1);

        assertTrue(result);
        verify(orderItemRepo, times(1)).deleteById(1);
    }
}
