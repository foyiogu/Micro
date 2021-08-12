package com.francis.photoapp.api.photoappusers.service;

import com.francis.photoapp.api.photoappusers.model.UserEntity;
import com.francis.photoapp.api.photoappusers.payload.dto.UserDTO;
import com.francis.photoapp.api.photoappusers.payload.request.UserRequest;
import com.francis.photoapp.api.photoappusers.payload.response.UserResponse;
import com.francis.photoapp.api.photoappusers.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

import static org.modelmapper.convention.MatchingStrategies.STRICT;
import static org.springframework.http.HttpStatus.CREATED;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public ResponseEntity<UserResponse> createUser(UserRequest userRequest) {
        UserDTO userDetails = mapper().map(userRequest, UserDTO.class);
        userDetails.setUserId(generateUUID());

        UserEntity userEntity = mapper().map(userDetails, UserEntity.class);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
        userRepository.save(userEntity);

        UserResponse userResponse = mapper().map(userEntity, UserResponse.class);

        return new ResponseEntity<>(userResponse,CREATED);
    }

    @Override
    public UserDTO getUserDetailsByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        return mapper().map(userEntity, UserDTO.class);
    }

    private String generateUUID(){
        return UUID.randomUUID().toString();
    }

    private ModelMapper mapper(){
        ModelMapper modelMapper =new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(STRICT);
        return modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username);

        if (userEntity == null) throw new UsernameNotFoundException(username);

        return new User(
                userEntity.getEmail(),
                userEntity.getEncryptedPassword(),
                true, // false for email verification if you need it later
                true,
                true,
                true,
                new ArrayList<>() //for authorities
        );
    }
}
