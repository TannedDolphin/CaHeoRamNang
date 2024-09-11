package cayxanh.GreencareTest.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ApiResponseTest {

    @Test
    void testApiResponseBuilder() {
        ApiResponse<String> response = ApiResponse.<String>builder()
                .code(200)
                .message("Success")
                .result("OK")
                .build();

        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertEquals("Success", response.getMessage());
        assertEquals("OK", response.getResult());
    }

    @Test
    void testApiResponseNoArgsConstructor() {
        ApiResponse<String> response = new ApiResponse<>();

        assertNotNull(response);
        assertEquals(1000, response.getCode()); // Default value
        assertNull(response.getMessage());
        assertNull(response.getResult());
    }

    @Test
    void testApiResponseAllArgsConstructor() {
        ApiResponse<String> response = new ApiResponse<>(200, "Success", "OK");

        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertEquals("Success", response.getMessage());
        assertEquals("OK", response.getResult());
    }

    @Test
    void testApiResponseSettersAndGetters() {
        ApiResponse<String> response = new ApiResponse<>();
        response.setCode(200);
        response.setMessage("Success");
        response.setResult("OK");

        assertEquals(200, response.getCode());
        assertEquals("Success", response.getMessage());
        assertEquals("OK", response.getResult());
    }
}
