package com.rovinn.moviereservation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserData {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank (message = "Username is required")
    @Column (nullable = false, unique = true)
    private String name;
    @Column (nullable = false, unique = true)
    @NotBlank (message = "Email is required")
    @Email (message = "Email must be valid")
    private String email;
    @Column (nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be 8 character longs")
    private String password;
    @Enumerated (EnumType.STRING)
    @Column (nullable = false)
    private Role role;

}
