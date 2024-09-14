package cayxanh.GreencareTest.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {

    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String productname;

    @Min(value = 0, message = "Giá sản phẩm phải lớn hơn hoặc bằng 0")
    private double productprice;

    private String productdescription;

    @Min(value = 0, message = "Số lượng tồn kho phải lớn hơn hoặc bằng 0")
    private int stockquantity;

    @NotNull(message = "Category ID không được để trống")
    private Integer categoryid;

    @NotNull(message="Ảnh sản phẩm rỗng")
    private Set<Long> imageIds;
}
