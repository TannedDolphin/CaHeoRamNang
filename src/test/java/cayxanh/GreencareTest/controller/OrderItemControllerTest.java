package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.entity.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderItemControllerTest {

    @Mock
    private OrderItemService orderItemService;

    @InjectMocks
    private OrderItemController orderItemController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrderItem() {
        OrderItem orderItem = new OrderItem();
        when(orderItemService.createOrderItem(1, 10)).thenReturn(orderItem);

        ResponseEntity<OrderItem> response = orderItemController.createOrderItem(1, 10);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderItem, response.getBody());
    }

    @Test
    public void testGetAllOrderItems() {
        List<OrderItem> orderItems = Arrays.asList(new OrderItem(), new OrderItem());
        when(orderItemService.getAllOrderItems()).thenReturn(orderItems);

        ResponseEntity<List<OrderItem>> response = orderItemController.getAllOrderItems();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderItems, response.getBody());
    }

    @Test
    public void testGetOrderItemById() {
        OrderItem orderItem = new OrderItem();
        when(orderItemService.getOrderItemById(1)).thenReturn(orderItem);

        ResponseEntity<OrderItem> response = orderItemController.getOrderItemById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderItem, response.getBody());
    }

    @Test
    public void testUpdateOrderItem() {
        OrderItem orderItem = new OrderItem();
        when(orderItemService.updateOrderItem(orderItem)).thenReturn(orderItem);

        ResponseEntity<OrderItem> response = orderItemController.updateOrderItem(orderItem);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderItem, response.getBody());
    }

    @Test
    public void testDeleteOrderItem() {
        doAnswer(invocation -> {
            // You can add any additional logic here if needed
            return null;
        }).when(orderItemService).deleteOrderItem(1);

        ResponseEntity<Void> response = orderItemController.deleteOrderItem(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(orderItemService, times(1)).deleteOrderItem(1);
    }

}
