package cayxanh.GreencareTest.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {


    String password;
    String email;
    String fullname;
    String phone;
    List<String> roles;

}
