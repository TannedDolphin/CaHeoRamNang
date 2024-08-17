package cayxanh.GreencareTest.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    @Size(min = 3, message = "username must be atleast 3 char")
    String username;
    @Size(min = 8, message = "password must be atleast 8 char")
    String password;
    @Email
    String email;
    @NotBlank(message = "full name must not be blank")
    String fullname;
    String phone;

}
