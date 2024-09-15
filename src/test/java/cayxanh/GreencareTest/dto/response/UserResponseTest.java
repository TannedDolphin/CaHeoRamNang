package cayxanh.GreencareTest.dto.response;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class UserResponseTest {

    @Test
    public void testNoArgsConstructor() {
        // Kiểm tra constructor không có tham số
        UserResponse response = new UserResponse();
        assertNull(response.getUserid(), "User ID should be null after no-args constructor");
        assertNull(response.getUsername(), "Username should be null after no-args constructor");
        assertNull(response.getEmail(), "Email should be null after no-args constructor");
        assertNull(response.getFullname(), "Full name should be null after no-args constructor");
        assertNull(response.getPhone(), "Phone should be null after no-args constructor");
        assertNull(response.getRoles(), "Roles should be null after no-args constructor");
        assertNull(response.getFeedbackList(), "Feedback list should be null after no-args constructor");
        assertNull(response.getOrdersList(), "Orders list should be null after no-args constructor");
    }

    @Test
    public void testAllArgsConstructor() {
        // Kiểm tra constructor với tất cả các tham số
        Set<String> roles = new HashSet<>(Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
        List<FeedbackResponse> feedbackList = Arrays.asList(new FeedbackResponse("Great service"), new FeedbackResponse("Needs improvement"));
        List<OrderResponse> ordersList = Arrays.asList(new OrderResponse("order123", 250.75), new OrderResponse("order456", 150.25));

        UserResponse response = new UserResponse("user123", "johndoe", "john.doe@example.com", "John Doe", "123-456-7890", roles, feedbackList, ordersList);

        assertEquals("user123", response.getUserid(), "User ID should be set via all-args constructor");
        assertEquals("johndoe", response.getUsername(), "Username should be set via all-args constructor");
        assertEquals("john.doe@example.com", response.getEmail(), "Email should be set via all-args constructor");
        assertEquals("John Doe", response.getFullname(), "Full name should be set via all-args constructor");
        assertEquals("123-456-7890", response.getPhone(), "Phone should be set via all-args constructor");
        assertEquals(roles, response.getRoles(), "Roles should be set via all-args constructor");
        assertEquals(feedbackList, response.getFeedbackList(), "Feedback list should be set via all-args constructor");
        assertEquals(ordersList, response.getOrdersList(), "Orders list should be set via all-args constructor");
    }

    @Test
    public void testSettersAndGetters() {
        // Kiểm tra setter và getter
        Set<String> roles = new HashSet<>(Arrays.asList("ROLE_USER"));
        List<FeedbackResponse> feedbackList = Arrays.asList(new FeedbackResponse("Good"), new FeedbackResponse("Average"));
        List<OrderResponse> ordersList = Arrays.asList(new OrderResponse("order789", 300.00));

        UserResponse response = new UserResponse();
        response.setUserid("user456");
        response.setUsername("janedoe");
        response.setEmail("jane.doe@example.com");
        response.setFullname("Jane Doe");
        response.setPhone("987-654-3210");
        response.setRoles(roles);
        response.setFeedbackList(feedbackList);
        response.setOrdersList(ordersList);

        assertEquals("user456", response.getUserid(), "User ID should be set and retrieved correctly");
        assertEquals("janedoe", response.getUsername(), "Username should be set and retrieved correctly");
        assertEquals("jane.doe@example.com", response.getEmail(), "Email should be set and retrieved correctly");
        assertEquals("Jane Doe", response.getFullname(), "Full name should be set and retrieved correctly");
        assertEquals("987-654-3210", response.getPhone(), "Phone should be set and retrieved correctly");
        assertEquals(roles, response.getRoles(), "Roles should be set and retrieved correctly");
        assertEquals(feedbackList, response.getFeedbackList(), "Feedback list should be set and retrieved correctly");
        assertEquals(ordersList, response.getOrdersList(), "Orders list should be set and retrieved correctly");
    }

    @Test
    public void testBuilderPattern() {
        // Kiểm tra builder pattern
        Set<String> roles = new HashSet<>(Arrays.asList("ROLE_USER", "ROLE_MANAGER"));
        List<FeedbackResponse> feedbackList = Arrays.asList(new FeedbackResponse("Excellent"), new FeedbackResponse("Needs improvement"));
        List<OrderResponse> ordersList = Arrays.asList(new OrderResponse("order111", 500.00));

        UserResponse response = UserResponse.builder()
                .userid("user789")
                .username("alexsmith")
                .email("alex.smith@example.com")
                .fullname("Alex Smith")
                .phone("321-654-9870")
                .roles(roles)
                .feedbackList(feedbackList)
                .ordersList(ordersList)
                .build();

        assertEquals("user789", response.getUserid(), "User ID should be set via builder");
        assertEquals("alexsmith", response.getUsername(), "Username should be set via builder");
        assertEquals("alex.smith@example.com", response.getEmail(), "Email should be set via builder");
        assertEquals("Alex Smith", response.getFullname(), "Full name should be set via builder");
        assertEquals("321-654-9870", response.getPhone(), "Phone should be set via builder");
        assertEquals(roles, response.getRoles(), "Roles should be set via builder");
        assertEquals(feedbackList, response.getFeedbackList(), "Feedback list should be set via builder");
        assertEquals(ordersList, response.getOrdersList(), "Orders list should be set via builder");
    }

    @Test
    public void testToString() {
        // Kiểm tra phương thức toString
        Set<String> roles = new HashSet<>(Arrays.asList("ROLE_USER"));
        List<FeedbackResponse> feedbackList = Arrays.asList(new FeedbackResponse("Okay"));
        List<OrderResponse> ordersList = Arrays.asList(new OrderResponse("order222", 150.0));

        UserResponse response = new UserResponse("user999", "maryjane", "mary.jane@example.com", "Mary Jane", "456-789-0123", roles, feedbackList, ordersList);
        String expected = "UserResponse(userid=user999, username=maryjane, email=mary.jane@example.com, fullname=Mary Jane, phone=456-789-0123, roles=[ROLE_USER], feedbackList=[FeedbackResponse(feedback=Okay)], ordersList=[OrderResponse(orderid=order222, totalPrice=150.0)])";
        assertEquals(expected, response.toString(), "toString() should return the correct string representation");
    }

    @Test
    public void testEqualsAndHashCode() {
        // Kiểm tra equals và hashCode
        Set<String> roles = new HashSet<>(Arrays.asList("ROLE_USER"));
        List<FeedbackResponse> feedbackList = Arrays.asList(new FeedbackResponse("Okay"));
        List<OrderResponse> ordersList = Arrays.asList(new OrderResponse("order333", 200.00));

        UserResponse response1 = new UserResponse("user111", "alice", "alice@example.com", "Alice", "123-456-7890", roles, feedbackList, ordersList);
        UserResponse response2 = new UserResponse("user111", "alice", "alice@example.com", "Alice", "123-456-7890", roles, feedbackList, ordersList);
        UserResponse response3 = new UserResponse("user222", "bob", "bob@example.com", "Bob", "098-765-4321", roles, feedbackList, ordersList);

        assertEquals(response1, response2, "Objects with the same fields should be equal");
        assertNotEquals(response1, response3, "Objects with different fields should not be equal");

        assertEquals(response1.hashCode(), response2.hashCode(), "Hash codes should be the same for equal objects");
        assertNotEquals(response1.hashCode(), response3.hashCode(), "Hash codes should be different for non-equal objects");
    }
}
