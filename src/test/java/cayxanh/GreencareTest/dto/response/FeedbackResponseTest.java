package cayxanh.GreencareTest.dto.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FeedbackResponseTest {

    @Test
    public void testNoArgsConstructor() {
        // Kiểm tra constructor không có tham số
        FeedbackResponse response = new FeedbackResponse();
        assertNull(response.getFeedback(), "Feedback should be null after no-args constructor");
    }

    @Test
    public void testAllArgsConstructor() {
        // Kiểm tra constructor có tham số
        FeedbackResponse response = new FeedbackResponse("Great service!");
        assertEquals("Great service!", response.getFeedback(), "Feedback should be set via all-args constructor");
    }

    @Test
    public void testSettersAndGetters() {
        // Kiểm tra setter và getter
        FeedbackResponse response = new FeedbackResponse();
        response.setFeedback("Excellent product!");

        assertEquals("Excellent product!", response.getFeedback(), "Feedback should be set and retrieved correctly");
    }

    @Test
    public void testBuilderPattern() {
        // Kiểm tra builder pattern
        FeedbackResponse response = FeedbackResponse.builder()
                .feedback("Very satisfied!")
                .build();

        assertEquals("Very satisfied!", response.getFeedback(), "Feedback should be set via builder");
    }

    @Test
    public void testToString() {
        // Kiểm tra phương thức toString
        FeedbackResponse response = new FeedbackResponse("Good experience");
        String expected = "FeedbackResponse(feedback=Good experience)";
        assertEquals(expected, response.toString(), "toString() should return the correct string representation");
    }

    @Test
    public void testEqualsAndHashCode() {
        // Kiểm tra equals và hashCode
        FeedbackResponse response1 = new FeedbackResponse("Feedback text");
        FeedbackResponse response2 = new FeedbackResponse("Feedback text");
        FeedbackResponse response3 = new FeedbackResponse("Different feedback");

        assertEquals(response1, response2, "Objects with the same fields should be equal");
        assertNotEquals(response1, response3, "Objects with different fields should not be equal");

        assertEquals(response1.hashCode(), response2.hashCode(), "Hash codes should be the same for equal objects");
        assertNotEquals(response1.hashCode(), response3.hashCode(), "Hash codes should be different for non-equal objects");
    }

}
