package cayxanh.GreencareTest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String userid;
    String username;
    String password;
    String email;
    String fullname;
    String phone;
    @ElementCollection
    Set<String> roles;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "userfeedback", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Feedback> feedbackList;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "userreview",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Review> reviewList;
    @OneToMany(fetch =FetchType.LAZY,mappedBy = "userorder",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Orders> ordersList;

}
