package cayxanh.GreencareTest.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String userid;
    String username;
    String email;
    String fullname;
    String phone;
    Set<String> roles;
    List<FeedbackResponse> feedbackList;
    List<OrderResponse> ordersList;

}
