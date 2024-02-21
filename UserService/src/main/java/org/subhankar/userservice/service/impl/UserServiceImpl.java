package org.subhankar.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.subhankar.userservice.model.DO.UserDO;
import org.subhankar.userservice.model.DTO.ResponseDTO;
import org.subhankar.userservice.model.DTO.SignupDTO;
import org.subhankar.userservice.model.DTO.SignupResponseDTO;
import org.subhankar.userservice.repository.UserRepository;
import org.subhankar.userservice.service.UserService;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public ResponseEntity<ResponseDTO> signup(SignupDTO signupDTO) {
        UserDO userDO = userRepository.save(UserDO.builder()
                .name(signupDTO.getName())
                .email(signupDTO.getEmail())
                .password(signupDTO.getPassword())
                .build());
        return ResponseEntity.ok(ResponseDTO.builder()
                .message("User created successfully!")
                .status(201)
                .data(SignupResponseDTO.builder()
                        .id(userDO.getId())
                        .name(userDO.getName())
                        .email(userDO.getEmail())
                        .build())
                .build());
    }

    @Override
    public ResponseEntity<ResponseDTO> getUserByEmail(String email) {
        Optional<UserDO> optional = userRepository.findByEmail(email);
        if(optional.isEmpty()){
            return ResponseEntity.ok(ResponseDTO.builder()
                    .message("User not found!")
                    .status(404)
                    .data(null)
                    .build());
        }
        UserDO userDO = optional.get();
        return ResponseEntity.ok(ResponseDTO.builder()
                .message("User found successfully!")
                .status(200)
                .data(userDO)
                .build());
    }
}
