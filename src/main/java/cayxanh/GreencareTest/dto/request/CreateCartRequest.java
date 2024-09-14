package cayxanh.GreencareTest.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateCartRequest {
    @NotNull(message = "User ID không được để trống")
    private String userid;

    @NotNull(message = "Danh sách sản phẩm trong giỏ hàng không được để trống")
    private List<CreateOrderItemRequest> orderItems;
}
