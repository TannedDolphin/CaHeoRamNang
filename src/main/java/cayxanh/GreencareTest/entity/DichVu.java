package cayxanh.GreencareTest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class DichVu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dichvuid;
    private String dichvuname;
    private String dichvudescription;

}
