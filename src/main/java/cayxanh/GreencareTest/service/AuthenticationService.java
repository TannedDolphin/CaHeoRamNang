package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.AuthenticationRequest;
import cayxanh.GreencareTest.exception.AppException;
import cayxanh.GreencareTest.exception.ErrorCode;
import cayxanh.GreencareTest.repo.UserRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepo userRepo;
    public boolean authenticate(AuthenticationRequest request) {
        var user = userRepo.findByUsername(request.getUsername()).orElseThrow(()->new AppException(ErrorCode.USER_NONEXISTED));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        return passwordEncoder.matches(request.getPassword(), user.getPassword());
    }
}
