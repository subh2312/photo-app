package org.subhankar.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.subhankar.userservice.model.DO.UserDO;
import org.subhankar.userservice.model.DTO.ResponseDTO;
import org.subhankar.userservice.model.DTO.SignupDTO;
import org.subhankar.userservice.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/status/check")
    public String hello() {
        return "Welcome to User Service!";
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> signup(@RequestBody SignupDTO signupDTO) {
        return userService.signup(signupDTO);
    }

    @GetMapping("/{email}")
    public ResponseEntity<ResponseDTO> getUser(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

//    @PostMapping("/validate")
//    public ResponseEntity<ResponseDTO> validateUser(@RequestBody SigninRequestDTO signinRequestDTO) {
//        return userService.validateUser(signinRequestDTO);
//    }
}
