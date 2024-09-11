package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.dto.request.ApiResponse;
import cayxanh.GreencareTest.dto.request.UserCreationRequest;
import cayxanh.GreencareTest.dto.request.UserUpdateRequest;
import cayxanh.GreencareTest.dto.response.UserResponse;
import cayxanh.GreencareTest.entity.User;
import cayxanh.GreencareTest.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testCreateUser() {
        UserCreationRequest request = new UserCreationRequest();
        User user = new User();
        when(userService.createUser(request)).thenReturn(user);

        ApiResponse<User> response = userController.createUser(request);

        assertEquals("Success", response.getMessage());
        assertEquals(user, response.getResult());
    }

    @Test
    public void testGetUsers() {
        List<UserResponse> users = Arrays.asList(new UserResponse(), new UserResponse());
        when(userService.getUsers()).thenReturn(users);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("testUser");

        ApiResponse<List<UserResponse>> response = userController.getUsers();

        assertEquals(users, response.getResult());
    }

    @Test
    public void testGetMyInfo() {
        UserResponse userResponse = new UserResponse();
        when(userService.getMyInfo()).thenReturn(userResponse);

        ApiResponse<UserResponse> response = userController.getMyInfo();

        assertEquals(userResponse, response.getResult());
    }

    @Test
    public void testGetUser() {
        UserResponse userResponse = new UserResponse();
        when(userService.getUser("1")).thenReturn(userResponse);

        ApiResponse<UserResponse> response = userController.getUser("1");

        assertEquals(userResponse, response.getResult());
    }

    @Test
    public void testUpdateUser() {
        UserUpdateRequest request = new UserUpdateRequest();
        UserResponse userResponse = new UserResponse();
        when(userService.updateUser("1", request)).thenReturn(userResponse);

        ApiResponse<UserResponse> response = userController.updateUser("1", request);

        assertEquals(userResponse, response.getResult());
    }

    @Test
    public void testDeleteUser() {
        doAnswer(_ -> {
            // You can add any additional logic here if needed
            return null;
        }).when(userService).deleteUser("1");

        ApiResponse<String> response = userController.deleteUser("1");

        assertEquals("User has been deleted", response.getResult());
        verify(userService, times(1)).deleteUser("1");
    }
}
