package cayxanh.GreencareTest.entity;

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
    @ManyToOne
    @JoinColumn(name = "categotyid",nullable = false,referencedColumnName = "categoryid")
    private Category categoryservice;
}
