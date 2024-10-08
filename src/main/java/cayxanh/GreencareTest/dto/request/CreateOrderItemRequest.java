package cayxanh.GreencareTest.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateOrderItemRequest {

    @NotNull(message="Tên sản phẩm rỗng")
    @NotEmpty(message = "Tên sản phẩm rỗng")
    @Size(min=5,max=50,message="Tên sản phẩm từ 5-50 ký tự")
    private String name;

    @NotNull(message="Giá sản phẩm rỗng")
    @Min(value = 0, message ="Giá sản phẩm từ 0 trở lên")
    private long price;

    @NotNull(message = "Số lượng sản phẩm rỗng")
    @Min(value = 1, message="Số lượng sản phẩm từ 1 trở lên")
    private int quantity;

    private long subTotal;
}
