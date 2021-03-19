package com.cancela.twitterclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotBlank(message = "Username is required")
    private String username;
    @NotEmpty(message = "Fantasy name is required")
    private String fantasyName;
    @NotBlank(message = "Password is required")
    private String password;
    @Email
    @NotEmpty(message = "Email is required")
    private String email;
    // Base64 of bite array of a compressed JPEG pic
    @Lob
    private String profilePic;
    private String bio;
    private String location;
    private String site;
    private Instant created;
    private boolean enabled;
}
