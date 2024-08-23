package cayxanh.GreencareTest.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderid;
    private double totalprice;
    private String orderstatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", nullable = false,referencedColumnName = "userid")
    private User userorder;
    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderItem> orderitems;
}
