package cayxanh.GreencareTest.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ApiResponseTest {

    @Test
    public void testApiResponseDefaultValues() {
        ApiResponse<String> response = new ApiResponse<>();
        assertEquals(1000, response.getCode());
        assertNull(response.getMessage());
        assertNull(response.getResult());
    }

    @Test
    public void testApiResponseSettersAndGetters() {
        ApiResponse<String> response = new ApiResponse<>();
        response.setCode(2000);
        response.setMessage("Success");
        response.setResult("Result");

        assertEquals(2000, response.getCode());
        assertEquals("Success", response.getMessage());
        assertEquals("Result", response.getResult());
    }

    @Test
    public void testApiResponseBuilder() {
        ApiResponse<String> response = ApiResponse.<String>builder()
                .code(2000)
                .message("Success")
                .result("Result")
                .build();

        assertEquals(2000, response.getCode());
        assertEquals("Success", response.getMessage());
        assertEquals("Result", response.getResult());
    }
}
