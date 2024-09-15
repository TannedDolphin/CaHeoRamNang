package cayxanh.GreencareTest.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDichVuRequest {

    @NotNull(message="Tiêu đề rỗng")
    @NotEmpty(message = "Tiêu đề trống")
    @Size(min=5,max=300,message="Độ dài tiêu đề từ 1-300 ký tự")
    private String dichvuname;


    @NotNull(message = "Mô tả rỗng")
    @NotEmpty(message = "Mô tả trống")
    @Size(min=5,max=3000,message= "Độ dài mô tả từ 1-300 ký tự")
    private String dichvudescription;
}
