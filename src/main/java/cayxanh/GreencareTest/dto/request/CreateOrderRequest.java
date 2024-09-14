package cayxanh.GreencareTest.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {

    @Min(value = 0, message = "Tổng giá trị đơn hàng phải lớn hơn hoặc bằng 0")
    private double totalprice;

    @NotBlank(message = "Trạng thái đơn hàng không được để trống")
    private String orderstatus;

    @NotNull(message = "User ID không được để trống")
    private String userid;

    @NotNull(message = "Danh sách sản phẩm trong đơn hàng không được để trống")
    private List<CreateOrderItemRequest> orderitems;

}
