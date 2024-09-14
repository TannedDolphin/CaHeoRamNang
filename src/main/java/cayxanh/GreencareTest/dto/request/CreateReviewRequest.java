package cayxanh.GreencareTest.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateReviewRequest {

    @NotNull(message = "Rating không được để trống")
    @Min(value = 1, message = "Rating tối thiểu là 1")
    @Max(value = 5, message = "Rating tối đa là 5")
    private Integer reviewrating;

    @NotBlank(message = "Nội dung đánh giá không được để trống")
    private String reviewtext;

    @NotNull(message = "Product ID không được để trống")
    private Integer productid;

    @NotNull(message = "User ID không được để trống")
    private String userid;
}
