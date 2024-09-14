package cayxanh.GreencareTest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class  Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartid;
}
