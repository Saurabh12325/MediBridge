package com.HospitalManagement.HospitalManagementSystem.Entity;
import com.HospitalManagement.HospitalManagementSystem.Entity.Type.AuthProviderType;
import com.HospitalManagement.HospitalManagementSystem.Entity.Type.RoleType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Table(name = "app_user", indexes = {
//        @Index(name = "idx_provider_id_provider_type", columnList = "providerId, providerType")
//})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(unique = true, nullable = false)
    private String username;
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}
