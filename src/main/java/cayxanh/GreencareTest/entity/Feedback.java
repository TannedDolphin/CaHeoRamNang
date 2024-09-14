package cayxanh.GreencareTest.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feedbackid;
    private String feedback;
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties({"password", "email", "fullname", "phone", "roles","feedbackList", "reviewList","ordersList"}) // Bỏ qua các trường này
    private User userfeedback;
}
