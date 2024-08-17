package cayxanh.GreencareTest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderid;
    private double totalprice;
    private String orderstatus;
    @ManyToOne
    @JoinColumn(name = "fullname")
    private User user;
}
