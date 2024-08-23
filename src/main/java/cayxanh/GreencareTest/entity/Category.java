package cayxanh.GreencareTest.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryid;
    private String categoryname;
    @OneToMany(mappedBy = "categoryproduct",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Product> products;
    @OneToMany(mappedBy = "categoryservice",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<DichVu> dichvu;
}
