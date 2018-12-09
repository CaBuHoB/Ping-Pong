package com.suai.pingpong.login.model;


import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    @Getter
    @Setter
    private int id;
    @Column(name = "email")
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    @Getter
    @Setter
    private String email;
    @Column(name = "username", unique = true)
    @NotEmpty(message = "*Please provide your email")
    @Getter
    @Setter
    private String username;
    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    @Getter
    @Setter
    private String password;
    @Column(name = "name")
    @NotEmpty(message = "*Please provide your name")
    @Getter
    @Setter
    private String name;
    @Column(name = "last_name")
    @NotEmpty(message = "*Please provide your last name")
    @Getter
    @Setter
    private String lastName;
    @Column(name = "active")
    @Getter
    @Setter
    private int active;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Getter
    @Setter
    private Set<Role> roles;
}
