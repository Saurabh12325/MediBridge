package com.HospitalManagement.HospitalManagementSystem.Entity;
import com.HospitalManagement.HospitalManagementSystem.Entity.Type.AuthProviderType;
import com.HospitalManagement.HospitalManagementSystem.Entity.Type.RoleType;
import com.HospitalManagement.HospitalManagementSystem.Security.RolePermissionMapping;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "app_user",indexes = {
        @Index(name = "idx_provider_id_provider_type",columnList = "providerId , providerType")
})

public class User implements UserDetails {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(unique = true, nullable = false)
    private String username;

    private String password;
    private String providerId;

    @Enumerated(EnumType.STRING)
    private AuthProviderType providerType;

    @ElementCollection(fetch = FetchType.EAGER)  //this annotation helpful for the creating of the new table for the roles
    @Enumerated(EnumType.STRING)
    Set<RoleType> roles =  new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return roles.stream()
//                .map(role->new SimpleGrantedAuthority("ROLE_"+role.name()))
//                .collect(Collectors.toList());
//    }
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        roles.forEach(
                role -> {
                    Set<SimpleGrantedAuthority> permissions = RolePermissionMapping.getAuthoritiesForRole(role);
                    authorities.addAll(permissions);
                    authorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
                }
        );
        return authorities;
    }
}
