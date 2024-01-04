package by.tms.findyourtutor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tb_user")
@Getter
@Setter
@ToString
public class UserStudent extends AbstractEntity {

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

}

