package cayxanh.GreencareTest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderitemid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderid", nullable = false,referencedColumnName = "orderid")
    private Orders orders;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productid",nullable = false,referencedColumnName = "productid")
    private Product orderitemproduct;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartid",nullable = false,referencedColumnName = "cartid")
    private Cart cartorderitem;
    private int quantity;

    public void setProduct(int id) {
    }

    public Product getProduct() {
        return this.orderitemproduct;
    }
}
