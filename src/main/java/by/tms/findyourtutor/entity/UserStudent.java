package by.tms.findyourtutor.entity;

import by.tms.findyourtutor.configuration.UserPrincipal;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.userdetails.User;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name="tb_user")
@Getter
@Setter
@ToString
public class UserStudent extends UserPrincipal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "username",nullable = false)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "lessonTime",nullable = false)
    public String lessonTime;

    @Column(name = "price",nullable = false)
    public String price;

    @Column(name = "language",nullable = false)
    public String language;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<Role> roles = new HashSet<>();


    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }


}

