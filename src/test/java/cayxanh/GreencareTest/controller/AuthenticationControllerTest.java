package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.dto.request.AuthenticationRequest;
import cayxanh.GreencareTest.dto.request.IntrospectRequest;
import cayxanh.GreencareTest.dto.response.AuthenticationResponse;
import cayxanh.GreencareTest.dto.response.IntrospectResponse;
import cayxanh.GreencareTest.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class AuthenticationControllerTest {

    @Mock
    AuthenticationService authenticationService;

    @InjectMocks
    AuthenticationController authenticationController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
    }

    @Test
    void authenticateToken() throws Exception {
        AuthenticationResponse authResponse = new AuthenticationResponse();
        when(authenticationService.authenticate(any(AuthenticationRequest.class))).thenReturn(authResponse);

        mockMvc.perform(post("/auth/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"user\",\"password\":\"pass\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").exists());
    }

    @Test
    void authenticateIntrospect() throws Exception {
        IntrospectResponse introspectResponse = new IntrospectResponse();
        when(authenticationService.introspect(any(IntrospectRequest.class))).thenReturn(introspectResponse);

        mockMvc.perform(post("/auth/introspect")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"token\":\"some-token\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").exists());
    }
}
