package by.tms.findyourtutor.entity;


import by.tms.findyourtutor.configuration.UserPrincipal;
import jakarta.persistence.*;
import lombok.*;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Set;

@Entity
@ToString
@Setter
@Getter
@Builder
@Table(name = "tb_user")
@AllArgsConstructor
@NoArgsConstructor
public class UserTutor extends UserPrincipal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "photo")
    private String photo;

    @Column(name = "language", nullable = false)
    private String country;




}

