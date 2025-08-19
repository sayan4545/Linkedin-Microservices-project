package com.sayan.linkedin.UserService.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLJoinTableRestriction;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
}
