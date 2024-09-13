package cayxanh.GreencareTest.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {

    @NotNull(message="Tên địa chỉ rỗng")
    @NotEmpty(message="Tên địa chỉ rỗng")
    private String address;

    @NotNull(message = "Email rỗng")
    @NotEmpty(message = "Email rỗng")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @NotNull(message="Số điện thoại rỗng")
    @NotEmpty(message="Số điện thoại rỗng")
    private String phone;

    private String note;

    private long totalPrice;

    private String username;

    private List<CreateOrderDetailRequest> orderDetails;

}
