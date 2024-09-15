package cayxanh.GreencareTest.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntrospectRequestTest {

    @Test
    public void testNoArgsConstructor() {
        // Sử dụng constructor không đối số
        IntrospectRequest request = new IntrospectRequest();
        assertNull(request.getToken(), "Token should be null after no-args constructor");
    }

    @Test
    public void testAllArgsConstructor() {
        // Sử dụng constructor với tất cả các đối số
        IntrospectRequest request = new IntrospectRequest("sampleToken123");
        assertEquals("sampleToken123", request.getToken(), "Token should be set correctly via all-args constructor");
    }

    @Test
    public void testSettersAndGetters() {
        // Kiểm tra các phương thức setter và getter
        IntrospectRequest request = new IntrospectRequest();
        request.setToken("newToken456");

        assertEquals("newToken456", request.getToken(), "Token should be set and retrieved correctly using setter and getter");
    }

    @Test
    public void testBuilderPattern() {
        // Sử dụng builder pattern để tạo đối tượng
        IntrospectRequest request = IntrospectRequest.builder()
                .token("builderToken789")
                .build();

        assertEquals("builderToken789", request.getToken(), "Token should be set correctly via builder");
    }

    @Test
    public void testToString() {
        // Kiểm tra phương thức toString (tự động sinh bởi @Data)
        IntrospectRequest request = new IntrospectRequest("sampleToken123");
        String expected = "IntrospectRequest(token=sampleToken123)";
        assertEquals(expected, request.toString(), "toString() should generate the correct string representation");
    }

    @Test
    public void testEqualsAndHashCode() {
        // Kiểm tra phương thức equals và hashCode (tự động sinh bởi @Data)
        IntrospectRequest request1 = new IntrospectRequest("token123");
        IntrospectRequest request2 = new IntrospectRequest("token123");
        IntrospectRequest request3 = new IntrospectRequest("differentToken");

        assertEquals(request1, request2, "Objects with the same token should be equal");
        assertNotEquals(request1, request3, "Objects with different tokens should not be equal");

        assertEquals(request1.hashCode(), request2.hashCode(), "Hash codes should be the same for equal objects");
        assertNotEquals(request1.hashCode(), request3.hashCode(), "Hash codes should be different for non-equal objects");
    }
}
