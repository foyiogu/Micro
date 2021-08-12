package com.francis.photoapp.api.photoappusers.controller;

import com.francis.photoapp.api.photoappusers.payload.request.UserRequest;
import com.francis.photoapp.api.photoappusers.payload.response.UserResponse;
import com.francis.photoapp.api.photoappusers.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final Environment env;
    private final UserService userService;

    @GetMapping("/status/check")
    public String status(){
        return "Working " + env.getProperty("local.server.port");
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest){
        return userService.createUser(userRequest);
    }

}
