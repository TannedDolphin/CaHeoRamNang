package cayxanh.GreencareTest.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderitemid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderid", nullable = false,referencedColumnName = "orderid")
    private Order orders;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productid",nullable = false,referencedColumnName = "productid")
    private Product orderitemproduct;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartid",nullable = false,referencedColumnName = "cartid")
    private Cart cartorderitem;
    private int quantity;
}
