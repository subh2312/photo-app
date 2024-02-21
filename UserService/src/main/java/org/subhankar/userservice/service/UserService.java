package org.subhankar.userservice.service;

import org.springframework.http.ResponseEntity;
import org.subhankar.userservice.model.DO.UserDO;
import org.subhankar.userservice.model.DTO.ResponseDTO;
import org.subhankar.userservice.model.DTO.SignupDTO;

public interface UserService {
    ResponseEntity<ResponseDTO> signup(SignupDTO signupDTO);

    ResponseEntity<ResponseDTO> getUserByEmail(String email);
}
