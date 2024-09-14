package cayxanh.GreencareTest.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryid;
    private String categoryname;
    @OneToMany(mappedBy = "categoryproduct", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Để quản lý tuần tự hóa JSON
    private Set<Product> products = new HashSet<>();
}
