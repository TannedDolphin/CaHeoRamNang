package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.dto.request.ApiResponse;
import cayxanh.GreencareTest.dto.request.UserCreationRequest;
import cayxanh.GreencareTest.dto.request.UserUpdateRequest;
import cayxanh.GreencareTest.dto.response.UserResponse;
import cayxanh.GreencareTest.entity.User;
import cayxanh.GreencareTest.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        apiResponse.setMessage("Success");
        return apiResponse;
    }
    @GetMapping
    List<User> getUsers() {
        return userService.getUsers();
    }
    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }
    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }
    @DeleteMapping("/{userId}")
    void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }

}
