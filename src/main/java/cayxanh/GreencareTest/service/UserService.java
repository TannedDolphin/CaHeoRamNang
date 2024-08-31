package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.UserCreationRequest;
import cayxanh.GreencareTest.dto.request.UserUpdateRequest;
import cayxanh.GreencareTest.dto.response.UserResponse;
import cayxanh.GreencareTest.entity.Cart;
import cayxanh.GreencareTest.entity.User;
import cayxanh.GreencareTest.exception.AppException;
import cayxanh.GreencareTest.exception.ErrorCode;
import cayxanh.GreencareTest.mapper.UserMapper;
import cayxanh.GreencareTest.repo.UserRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepo userRepo;
    UserMapper userMapper;
    CartService cartService;
    public User createUser(UserCreationRequest request) {
        if(userRepo.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser= userRepo.save(user);
        cartService.addCart(savedUser);
        return savedUser;
    }
    public List<User> getUsers() {
        return userRepo.findAll();
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
}
