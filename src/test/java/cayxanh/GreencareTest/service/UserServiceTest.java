package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.UserCreationRequest;
import cayxanh.GreencareTest.dto.request.UserUpdateRequest;
import cayxanh.GreencareTest.dto.response.UserResponse;
import cayxanh.GreencareTest.entity.User;
import cayxanh.GreencareTest.enums.Role;
import cayxanh.GreencareTest.mapper.UserMapper;
import cayxanh.GreencareTest.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private UserMapper userMapper;

    @Mock
    private CartService cartService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserCreationRequest userCreationRequest;
    private UserUpdateRequest userUpdateRequest;
    private UserResponse userResponse;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .userid("1")
                .username("testuser")
                .password("password")
                .email("testuser@example.com")
                .fullname("Test User")
                .phone("1234567890")
                .roles(new HashSet<>(List.of(Role.USER.name())))
                .build();

        userCreationRequest = UserCreationRequest.builder()
                .username("testuser")
                .password("password")
                .email("testuser@example.com")
                .fullname("Test User")
                .phone("1234567890")
                .build();

        userUpdateRequest = UserUpdateRequest.builder()
                .password("newpassword")
                .email("newemail@example.com")
                .fullname("Updated User")
                .phone("0987654321")
                .build();

        userResponse = UserResponse.builder()
                .userid("1")
                .username("testuser")
                .email("testuser@example.com")
                .fullname("Test User")
                .phone("1234567890")
                .roles(new HashSet<>(List.of(Role.USER.name())))
                .build();
    }

    @Test
    void testCreateUser() {
        when(userRepo.existsByUsername(userCreationRequest.getUsername())).thenReturn(false);
        when(userMapper.toUser(userCreationRequest)).thenReturn(user);
        when(passwordEncoder.encode(userCreationRequest.getPassword())).thenReturn("encodedPassword");
        when(userRepo.save(user)).thenReturn(user);

        User createdUser = userService.createUser(userCreationRequest);

        assertNotNull(createdUser);
        assertEquals("testuser", createdUser.getUsername());
        verify(userRepo, times(1)).existsByUsername(userCreationRequest.getUsername());
        verify(userMapper, times(1)).toUser(userCreationRequest);
        verify(passwordEncoder, times(1)).encode(userCreationRequest.getPassword());
        verify(userRepo, times(1)).save(user);
        verify(cartService, times(1)).addCart(user);
    }

    @Test
    void testGetMyInfo() {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("testuser");
        when(userRepo.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(userMapper.toUserResponse(user)).thenReturn(userResponse);

        UserResponse myInfo = userService.getMyInfo();

        assertNotNull(myInfo);
        assertEquals("testuser", myInfo.getUsername());
        verify(userRepo, times(1)).findByUsername("testuser");
        verify(userMapper, times(1)).toUserResponse(user);
    }

    @Test
    void testGetUsers() {
        when(userRepo.findAll()).thenReturn(List.of(user));
        when(userMapper.toUserResponse(user)).thenReturn(userResponse);

        List<UserResponse> users = userService.getUsers();

        assertNotNull(users);
        assertEquals(1, users.size());
        verify(userRepo, times(1)).findAll();
        verify(userMapper, times(1)).toUserResponse(user);
    }

    @Test
    void testGetUser() {
        when(userRepo.findById("1")).thenReturn(Optional.of(user));
        when(userMapper.toUserResponse(user)).thenReturn(userResponse);

        UserResponse foundUser = userService.getUser("1");

        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
        verify(userRepo, times(1)).findById("1");
        verify(userMapper, times(1)).toUserResponse(user);
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepo).deleteById("1");

        userService.deleteUser("1");

        verify(userRepo, times(1)).deleteById("1");
    }

    @Test
    void testUpdateUser() {
        when(userRepo.findById("1")).thenReturn(Optional.of(user));
        doNothing().when(userMapper).updateUser(user, userUpdateRequest);
        when(userRepo.save(user)).thenReturn(user);
        when(userMapper.toUserResponse(user)).thenReturn(userResponse);

        UserResponse updatedUser = userService.updateUser("1", userUpdateRequest);

        assertNotNull(updatedUser);
        assertEquals("testuser", updatedUser.getUsername());
        verify(userRepo, times(1)).findById("1");
        verify(userMapper, times(1)).updateUser(user, userUpdateRequest);
        verify(userRepo, times(1)).save(user);
        verify(userMapper, times(1)).toUserResponse(user);
    }
}
