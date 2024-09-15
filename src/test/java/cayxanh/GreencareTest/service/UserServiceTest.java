package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.UserCreationRequest;
import cayxanh.GreencareTest.dto.request.UserUpdateRequest;
import cayxanh.GreencareTest.dto.response.UserResponse;
import cayxanh.GreencareTest.entity.User;
import cayxanh.GreencareTest.enums.Role;
import cayxanh.GreencareTest.exception.AppException;
import cayxanh.GreencareTest.exception.ErrorCode;
import cayxanh.GreencareTest.mapper.UserMapper;
import cayxanh.GreencareTest.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        UserCreationRequest request = new UserCreationRequest();
        request.setUsername("testuser");
        request.setPassword("password");

        User user = new User();
        user.setUsername("testuser");
        user.setPassword("encodedPassword");
        user.setRoles(Set.of(Role.USER.name()));

        when(userRepo.existsByUsername(request.getUsername())).thenReturn(false);
        when(userMapper.toUser(request)).thenReturn(user);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(userRepo.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(request);

        assertNotNull(createdUser);
        assertEquals("testuser", createdUser.getUsername());
        assertEquals("encodedPassword", createdUser.getPassword());
        assertTrue(createdUser.getRoles().contains(Role.USER.name()));

        verify(userRepo, times(1)).existsByUsername(request.getUsername());
        verify(userMapper, times(1)).toUser(request);
        verify(passwordEncoder, times(1)).encode(request.getPassword());
        verify(userRepo, times(1)).save(any(User.class));
    }

    @Test
    public void testGetMyInfo() {
        User user = new User();
        user.setUsername("testuser");

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(authentication.getName()).thenReturn("testuser");
        when(userRepo.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(userMapper.toUserResponse(user)).thenReturn(new UserResponse());

        UserResponse userInfo = userService.getMyInfo();

        assertNotNull(userInfo);
        verify(userRepo, times(1)).findByUsername("testuser");
        verify(userMapper, times(1)).toUserResponse(user);
    }

    @Test
    public void testUpdateUser() {
        UserUpdateRequest request = new UserUpdateRequest();
        request.setFullname("Updated Name");

        User user = new User();
        user.setUserid("1");
        user.setFullname("Old Name");

        when(userRepo.findById("1")).thenReturn(Optional.of(user));
        doNothing().when(userMapper).updateUser(user, request);
        when(userRepo.save(user)).thenReturn(user);
        when(userMapper.toUserResponse(user)).thenReturn(new UserResponse());

        UserResponse updatedUser = userService.updateUser("1", request);

        assertNotNull(updatedUser);
        verify(userRepo, times(1)).findById("1");
        verify(userMapper, times(1)).updateUser(user, request);
        verify(userRepo, times(1)).save(user);
        verify(userMapper, times(1)).toUserResponse(user);
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userRepo).deleteById("1");

        userService.deleteUser("1");

        verify(userRepo, times(1)).deleteById("1");
    }
}
