package cayxanh.GreencareTest.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartid;
    @OneToOne
    private User user;
    @OneToMany
    private List<OrderItem> orderItems;
}
