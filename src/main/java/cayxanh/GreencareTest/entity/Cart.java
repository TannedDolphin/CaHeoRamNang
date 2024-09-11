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
    @OneToOne
    @JoinColumn(name = "userid",nullable = false,referencedColumnName = "userid")
    @JsonManagedReference
    private User user;
    @OneToMany(mappedBy = "cartorderitem",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderItem> orderItems;

    public void setId(int i) {
        this.cartid = i;
    }

    public int getId() {
        return cartid;
    }
}
