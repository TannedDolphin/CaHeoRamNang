package cayxanh.GreencareTest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reviewid;
    private int reviewrating;
    private String reviewtext;
    @ManyToOne
    @JoinColumn(name = "productname")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "fullname")
    private User user;
}
