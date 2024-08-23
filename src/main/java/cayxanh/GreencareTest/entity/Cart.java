package cayxanh.GreencareTest.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class  Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartid;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "userid",nullable = false,referencedColumnName = "userid")
    private User usercart;
    @OneToMany(mappedBy = "cartorderitem",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderItem> orderItems;
}
