package cayxanh.GreencareTest.dto.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IntrospectResponseTest {

    @Test
    void testIntrospectResponseBuilder() {
        IntrospectResponse response = IntrospectResponse.builder()
                .valid(true)
                .build();

        assertNotNull(response);
        assertTrue(response.isValid());
    }

    @Test
    void testIntrospectResponseNoArgsConstructor() {
        IntrospectResponse response = new IntrospectResponse();

        assertNotNull(response);
        assertFalse(response.isValid());
    }

    @Test
    void testIntrospectResponseAllArgsConstructor() {
        IntrospectResponse response = new IntrospectResponse(true);

        assertNotNull(response);
        assertTrue(response.isValid());
    }

    @Test
    void testIntrospectResponseSettersAndGetters() {
        IntrospectResponse response = new IntrospectResponse();
        response.setValid(true);

        assertTrue(response.isValid());
    }
}
