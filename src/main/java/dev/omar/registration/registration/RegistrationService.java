package dev.omar.registration.registration;

import dev.omar.registration.auth.AuthenticationResponse;
import dev.omar.registration.security.PasswordEncoder;
import dev.omar.registration.security.config.JwtService;
import dev.omar.registration.user.Role;
import dev.omar.registration.user.UserRepository;
import dev.omar.registration.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationResponse register(RegistrationRequest request) {
        var user = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(passwordEncoder.bCryptPasswordEncoder().encode(request.getPassword()))
                .role(Role.FREELANCER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
