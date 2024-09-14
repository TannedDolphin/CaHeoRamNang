package cayxanh.GreencareTest.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrderItemRequest {

    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String name;

    @Min(value = 0, message = "Giá sản phẩm phải lớn hơn hoặc bằng 0")
    private long price;

    @Min(value = 1, message = "Số lượng sản phẩm phải lớn hơn hoặc bằng 1")
    private int quantity;

    @Min(value = 0, message = "Tổng tiền sản phẩm phải lớn hơn hoặc bằng 0")
    private long subTotal;

    @NotNull(message = "Order ID không được để trống")
    private int orderid;
    @NotNull(message = "Cart ID không được để trống")
    private int cartid;
}
