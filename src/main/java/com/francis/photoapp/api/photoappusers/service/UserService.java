package com.francis.photoapp.api.photoappusers.service;

import com.francis.photoapp.api.photoappusers.payload.dto.UserDTO;
import com.francis.photoapp.api.photoappusers.payload.request.UserRequest;
import com.francis.photoapp.api.photoappusers.payload.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    ResponseEntity<UserResponse> createUser(UserRequest userRequest);
    UserDTO getUserDetailsByEmail(String email);
}
