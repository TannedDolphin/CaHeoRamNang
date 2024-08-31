package cayxanh.GreencareTest.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userid;
    private String username;
    private String password;
    private String email;
    private String fullname;
    private String phone;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "userfeedback", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Feedback> feedbackList;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "userreview",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Review> reviewList;
    @OneToMany(fetch =FetchType.LAZY,mappedBy = "userorder",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Order> orderList;
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cart cart;
}
