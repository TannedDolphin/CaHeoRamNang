package cayxanh.GreencareTest.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTagRequest {


    @NotNull(message = "Tên nhãn rỗng")
    @NotEmpty(message = "Tên nhãn rỗng")
    private String name;
}
