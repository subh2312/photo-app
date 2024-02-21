package org.subhankar.authservice.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.subhankar.authservice.model.DTO.ResponseDTO;

@FeignClient(name = "USER-SERVICE")
public interface UserIntegration {
    @GetMapping("/users/{email}")
    ResponseEntity<ResponseDTO> getUserByEmail(@PathVariable String email);
}
