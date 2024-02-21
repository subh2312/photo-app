package org.subhankar.userservice.model.DO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserDO {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @JsonIgnore
    private String salt;

    @Value("${application.pepper}")
    @Transient
    @JsonIgnore
    private String pepper;

    @PrePersist
    @PreUpdate
    private void hashPassword() {
        this.salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(this.password+pepper, this.salt);
        setPassword(hashedPassword);
    }

    public boolean checkPassword(String password) {
        return BCrypt.hashpw(password+pepper, this.salt).equals(this.password);
    }
}
