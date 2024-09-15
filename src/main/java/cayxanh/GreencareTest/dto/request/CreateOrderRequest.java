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

    @NotNull(message="Tên khách hàng rỗng")
    @NotEmpty(message="Tên khách hàng rỗng")
    @Size(min=3,max=100,message=" Tên khách hàng từ 3-100 ký tự")
    private String fullname;

    @NotNull(message="địa chỉ rỗng")
    @NotEmpty(message="địa chỉ rỗng")
    private String address;

    @NotNull(message = "Email rỗng")
    @NotEmpty(message = "Email rỗng")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @NotNull(message="Số điện thoại rỗng")
    @NotEmpty(message="Số điện thoại rỗng")
    private String phone;

    private double totalprice;

    private String orderstatus;

    private String username;

    private List<CreateOrderItemRequest> orderitems;

}
