package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.UserCreationRequest;
import cayxanh.GreencareTest.dto.request.UserUpdateRequest;
import cayxanh.GreencareTest.dto.response.UserResponse;
import cayxanh.GreencareTest.entity.Cart;
import cayxanh.GreencareTest.entity.User;
import cayxanh.GreencareTest.enums.Role;
import cayxanh.GreencareTest.exception.AppException;
import cayxanh.GreencareTest.exception.ErrorCode;
import cayxanh.GreencareTest.mapper.UserMapper;
import cayxanh.GreencareTest.repo.UserRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepo userRepo;
    UserMapper userMapper;
    CartService cartService;
    PasswordEncoder passwordEncoder;
    public User createUser(UserCreationRequest request) {
        if(userRepo.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);
        User savedUser= userRepo.save(user);
        cartService.addCart(savedUser);
        return savedUser;
    }
    public List<UserResponse> getUsers() {
        log.info("getUsers");
        return userRepo.findAll().stream().map(userMapper::toUserResponse).toList();
    }
    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
    }
    public void deleteUser(String id) {
        userRepo.deleteById(id);
    }
    public UserResponse  updateUser(String userId, UserUpdateRequest request) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUser(user,request);
        return userMapper.toUserResponse(userRepo.save(user));
    }
    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepo.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NONEXISTED));

        return userMapper.toUserResponse(user);
    }
}
