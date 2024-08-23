package cayxanh.GreencareTest.entity;

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
    private Product productreview;
    @ManyToOne
    @JoinColumn(name = "userid",nullable = false,referencedColumnName = "userid")
    private User userreview;
}
