package org.ecommerceshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Username is mandatory")
    @Size(max = 50, message = "Username should not be greater than 50 characters")
    private String username;
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password should be at least 8 characters")
    private String password;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    public User() {
    }


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
