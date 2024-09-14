package cayxanh.GreencareTest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewid;
    private int reviewrating;
    private String reviewtext;
    @ManyToOne
    @JoinColumn(name = "productid",nullable = false,referencedColumnName = "productid")
    @JsonIgnoreProperties({"categoryid","reviews","productname","productprice","stockquantity","image_id","productdescription","product_image","images"})
    private Product productreview;
}
