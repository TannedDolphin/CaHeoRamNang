package cayxanh.GreencareTest.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LogoutRequestTest {

    @Test
    public void testNoArgsConstructor() {
        // Kiểm tra constructor không có tham số (No-args constructor)
        LogoutRequest request = new LogoutRequest();
        assertNull(request.getToken(), "Token should be null after using no-args constructor");
    }

    @Test
    public void testAllArgsConstructor() {
        // Kiểm tra constructor với tất cả các tham số (All-args constructor)
        LogoutRequest request = new LogoutRequest("sampleToken");
        assertEquals("sampleToken", request.getToken(), "Token should be set correctly via all-args constructor");
    }

    @Test
    public void testSettersAndGetters() {
        // Kiểm tra setter và getter cho token
        LogoutRequest request = new LogoutRequest();
        request.setToken("newToken");

        assertEquals("newToken", request.getToken(), "Token should be set and retrieved correctly using setter and getter");
    }

    @Test
    public void testBuilderPattern() {
        // Kiểm tra builder pattern
        LogoutRequest request = LogoutRequest.builder()
                .token("builderToken")
                .build();

        assertEquals("builderToken", request.getToken(), "Token should be set correctly via builder pattern");
    }

    @Test
    public void testToString() {
        // Kiểm tra phương thức toString (tự động sinh bởi @Data)
        LogoutRequest request = new LogoutRequest("tokenValue");
        String expected = "LogoutRequest(token=tokenValue)";
        assertEquals(expected, request.toString(), "toString() should generate the correct string representation");
    }

    @Test
    public void testEqualsAndHashCode() {
        // Kiểm tra phương thức equals và hashCode (tự động sinh bởi @Data)
        LogoutRequest request1 = new LogoutRequest("token1");
        LogoutRequest request2 = new LogoutRequest("token1");
        LogoutRequest request3 = new LogoutRequest("token2");

        // Các đối tượng có cùng giá trị token phải bằng nhau
        assertEquals(request1, request2, "Objects with the same token should be equal");
        // Các đối tượng có token khác nhau không được bằng nhau
        assertNotEquals(request1, request3, "Objects with different tokens should not be equal");

        // hashCode phải giống nhau cho các đối tượng bằng nhau
        assertEquals(request1.hashCode(), request2.hashCode(), "Hash codes should be the same for equal objects");
        // hashCode phải khác nhau cho các đối tượng không bằng nhau
        assertNotEquals(request1.hashCode(), request3.hashCode(), "Hash codes should be different for non-equal objects");
    }
}
