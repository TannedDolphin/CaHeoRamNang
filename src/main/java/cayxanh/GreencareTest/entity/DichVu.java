package cayxanh.GreencareTest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class DichVu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int dichvuid;
    private String dichvuname;
    private String dichvudescription;
    private double dichvuprice;
    @ManyToOne
    @JoinColumn(name = "categotyid")
    private Category category;
}
