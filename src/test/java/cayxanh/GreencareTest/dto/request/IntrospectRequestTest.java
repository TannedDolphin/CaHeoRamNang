package cayxanh.GreencareTest.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IntrospectRequestTest {

    @Test
    void testIntrospectRequestBuilder() {
        IntrospectRequest request = IntrospectRequest.builder()
                .token("sampleToken")
                .build();

        assertNotNull(request);
        assertEquals("sampleToken", request.getToken());
    }

    @Test
    void testIntrospectRequestNoArgsConstructor() {
        IntrospectRequest request = new IntrospectRequest();

        assertNotNull(request);
        assertNull(request.getToken());
    }

    @Test
    void testIntrospectRequestAllArgsConstructor() {
        IntrospectRequest request = new IntrospectRequest("sampleToken");

        assertNotNull(request);
        assertEquals("sampleToken", request.getToken());
    }

    @Test
    void testIntrospectRequestSettersAndGetters() {
        IntrospectRequest request = new IntrospectRequest();
        request.setToken("sampleToken");

        assertEquals("sampleToken", request.getToken());
    }
}
