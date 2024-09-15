package cayxanh.GreencareTest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateFeedbackRequest {
    @NotBlank(message = "Nội dung phản hồi không được để trống")
    private String feedback;

    @NotNull(message = "User ID không được để trống")
    private String userid;


}
