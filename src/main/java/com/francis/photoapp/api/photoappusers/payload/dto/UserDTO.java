package com.francis.photoapp.api.photoappusers.payload.dto;


import lombok.Data;
import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -953297098295050686L;

    private String userId;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String encryptedPassword;
}
