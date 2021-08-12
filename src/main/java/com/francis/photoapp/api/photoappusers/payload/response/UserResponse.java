package com.francis.photoapp.api.photoappusers.payload.response;

import lombok.Data;

@Data
public class UserResponse {
    private String firstName;
    private String lastName;
    private String userId;
    private String email;
}
