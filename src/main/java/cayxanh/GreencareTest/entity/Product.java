package cayxanh.GreencareTest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productid;
    private String productname;
    private double productprice;
    private String productdescription;
    private int stockquantity;
    private String productimage;
    @ManyToOne
    @JoinColumn(name = "categoryname")
    private Category category;
}
