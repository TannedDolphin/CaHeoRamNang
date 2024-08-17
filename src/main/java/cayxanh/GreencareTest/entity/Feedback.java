package cayxanh.GreencareTest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int feedbackid;
    private String feedback;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;
}
