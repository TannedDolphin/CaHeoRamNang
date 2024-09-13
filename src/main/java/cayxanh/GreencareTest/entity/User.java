package cayxanh.GreencareTest.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
    @ManyToMany
    Set<Role> roles;
}
