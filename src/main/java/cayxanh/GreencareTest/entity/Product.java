package cayxanh.GreencareTest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
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
    @ManyToOne
    @JoinColumn(name = "categoryid", nullable = false)
    @JsonBackReference // Để tránh tuần tự hóa ngược lại Category
    private Category categoryproduct;
    @OneToMany(mappedBy = "productreview",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Review> reviews;
    @ManyToMany
    @JoinTable(name = "product_image",joinColumns = @JoinColumn(name="product_id"),inverseJoinColumns = @JoinColumn(name="image_id"))
    private Set<Image> images = new HashSet<>();
}
