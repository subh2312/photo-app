package org.subhankar.authservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.subhankar.authservice.integration.UserIntegration;
import org.subhankar.authservice.model.DO.UserDO;
import org.subhankar.authservice.model.DTO.ResponseDTO;

import java.util.List;

@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserIntegration userIntegration;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResponseEntity<ResponseDTO> response = userIntegration.getUserByEmail(username);
        ResponseDTO responseDTO = response.getBody();
        UserDO user = (UserDO) responseDTO.getData();
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRole().toString())));
        }
        return null;
    }
}
