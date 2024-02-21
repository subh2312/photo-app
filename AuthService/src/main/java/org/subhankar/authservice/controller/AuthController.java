package org.subhankar.authservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.subhankar.authservice.integration.UserIntegration;
import org.subhankar.authservice.model.DTO.LoginRequestDTO;
import org.subhankar.authservice.model.DTO.ResponseDTO;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserIntegration userIntegration;

        @PostMapping("/login")
        public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {

            return null;

        }

        @GetMapping("/user/{email}")
        public ResponseEntity<ResponseDTO> getUserByEmail(@PathVariable String email) {
            return userIntegration.getUserByEmail(email);
        }
}
