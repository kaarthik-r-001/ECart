package com.assign1.demo.entity;


import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;


import java.util.Date;
@Data
public class UserRequestDTO {

    int id;
    @Valid
    @NotNull
//    @NotEmpty
    String username;
    String firstName;
    Date dob;



    private String roles;

    private String password;

    public UserRequestDTO(String username, String firstName, Date dob, String roles, String password) {
        this.username = username;
        this.firstName = firstName;
        this.dob = dob;
        this.roles = roles;
        this.password = password;
    }

}
