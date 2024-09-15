package cayxanh.GreencareTest.dto.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderResponseTest {

    @Test
    public void testNoArgsConstructor() {
        // Kiểm tra constructor không có tham số
        OrderResponse response = new OrderResponse();
        assertNull(response.getOrderid(), "Order ID should be null after no-args constructor");
        assertNull(response.getTotalPrice(), "Total Price should be null after no-args constructor");
    }

    @Test
    public void testAllArgsConstructor() {
        // Kiểm tra constructor với tất cả các tham số
        OrderResponse response = new OrderResponse("12345", 99.99);
        assertEquals("12345", response.getOrderid(), "Order ID should be set via all-args constructor");
        assertEquals(99.99, response.getTotalPrice(), "Total Price should be set via all-args constructor");
    }

    @Test
    public void testSettersAndGetters() {
        // Kiểm tra setter và getter
        OrderResponse response = new OrderResponse();
        response.setOrderid("54321");
        response.setTotalPrice(49.99);

        assertEquals("54321", response.getOrderid(), "Order ID should be set and retrieved correctly");
        assertEquals(49.99, response.getTotalPrice(), "Total Price should be set and retrieved correctly");
    }

    @Test
    public void testBuilderPattern() {
        // Kiểm tra builder pattern
        OrderResponse response = OrderResponse.builder()
                .orderid("67890")
                .totalPrice(129.99)
                .build();

        assertEquals("67890", response.getOrderid(), "Order ID should be set via builder");
        assertEquals(129.99, response.getTotalPrice(), "Total Price should be set via builder");
    }

    @Test
    public void testToString() {
        // Kiểm tra phương thức toString
        OrderResponse response = new OrderResponse("98765", 79.99);
        String expected = "OrderResponse(orderid=98765, totalPrice=79.99)";
        assertEquals(expected, response.toString(), "toString() should return the correct string representation");
    }

    @Test
    public void testEqualsAndHashCode() {
        // Kiểm tra equals và hashCode
        OrderResponse response1 = new OrderResponse("11111", 59.99);
        OrderResponse response2 = new OrderResponse("11111", 59.99);
        OrderResponse response3 = new OrderResponse("22222", 99.99);

        assertEquals(response1, response2, "Objects with the same fields should be equal");
        assertNotEquals(response1, response3, "Objects with different fields should not be equal");

        assertEquals(response1.hashCode(), response2.hashCode(), "Hash codes should be the same for equal objects");
        assertNotEquals(response1.hashCode(), response3.hashCode(), "Hash codes should be different for non-equal objects");
    }
}
