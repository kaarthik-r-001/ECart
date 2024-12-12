package com.assign1.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(unique = true,nullable = false)

    String username;
    @Column(name="first_name")
    String firstName;
    Date dob;
    String env_name;



    @Column(nullable = false, name = "ROLES")
    private String roles;
    @Column(nullable = false, name = "PASSWORD")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RefreshTokenEntity> refreshTokens;

    public User(String username, String firstName, Date dob, String env_name, String roles, String password) {
        this.username = username;
        this.firstName = firstName;
        this.dob = dob;
        this.env_name = env_name;
        this.roles = roles;
        this.password = password;
    }

}
