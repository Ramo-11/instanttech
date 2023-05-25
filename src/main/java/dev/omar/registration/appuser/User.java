package dev.omar.registration.appuser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "\"User\"")
@Table(uniqueConstraints = { @UniqueConstraint(name = "email_unique", columnNames = "email") })
public class User implements UserDetails {

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "user_sequence")
    @Column(updatable = false)
    private Long id;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String name;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String username;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String email;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "TEXT")
    private Role role;
    private Boolean locked;
    private Boolean enabled;

    public User(String name, String username, String email, String password, Role role, Boolean locked, Boolean enabled) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.locked = locked;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
