package cayxanh.GreencareTest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderitemid;
    @ManyToOne
    @JoinColumn(name = "orderid")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "productname")
    private Product product;
    private int quantity;
}
