package com.assign1.demo.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
@Data
@NoArgsConstructor

public class UserResponseDTO {
    int id;
    @NotNull
    @Size(min = 1, message = "Username must not be empty")
    String username;
    String firstName;

    private String roles;

    private String password;


    public UserResponseDTO(String username, String firstName, String roles, String password, Date dob, String env_name) {
        this.username = username;
        this.firstName = firstName;
        this.roles = roles;
        this.password = password;
        this.dob = dob;
        this.env_name = env_name;
    }

    Date dob;
    String env_name;
}
