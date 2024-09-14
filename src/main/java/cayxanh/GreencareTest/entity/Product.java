package cayxanh.GreencareTest.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productid;
    @Column(name = "productname")
    private String productname;
    private double productprice;
    private String productdescription;
    private int stockquantity;
    private String productimage;
    @ManyToOne
    @JoinColumn(name = "categoryid",nullable = false,referencedColumnName = "categoryid")
    private Category categoryproduct;
    @OneToMany(mappedBy = "productreview",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Review> reviews;
}
