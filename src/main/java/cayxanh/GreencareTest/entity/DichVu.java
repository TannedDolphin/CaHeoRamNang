package cayxanh.GreencareTest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="service")
public class DichVu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @Column(name = "description",columnDefinition = "TEXT")
    private String description;
    @Column(name = "content",columnDefinition = "TEXT")
    private String content;
    @ManyToOne
    @JoinColumn(name="image_id")
    private Image image;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="dichvu_tag",joinColumns = @JoinColumn(name="dichvu_id"),inverseJoinColumns = @JoinColumn(name="tag_id"))
    private Set<Tag> tags = new HashSet<>();

}