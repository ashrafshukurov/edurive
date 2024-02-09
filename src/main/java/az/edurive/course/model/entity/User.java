package az.edurive.course.model.entity;

import az.edurive.course.model.enums.RoleType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Table(name = "users")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "user_name", length = 25, nullable = false)
    String name;
    @Column(name = "user_surname", length = 35, nullable = false)
    String surname;
    @Column(name = "user_gmail", nullable = false)
    String gmail;
    @Column(nullable = false)
    String password;
    String picture;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role_type", nullable = false)
    RoleType roleType;


}
