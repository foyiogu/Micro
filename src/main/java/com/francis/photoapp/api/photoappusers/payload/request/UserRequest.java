package com.francis.photoapp.api.photoappusers.payload.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRequest {

    @NotNull(message = "First name cannot be empty")
    @Size(min = 2, message = "First name cannot be less than two characters")
    private String firstName;

    @NotNull(message = "Last name cannot be empty")
    @Size(min = 2, message = "last name cannot be less than two characters")
    private String lastName;

    @NotNull(message = "Password cannot be empty")
    @Size(min = 2, max = 16, message = "Password cannot be less than two characters")
    private String password;

    @NotNull(message = "First name cannot be empty")
    @Email
    private String email;
}
