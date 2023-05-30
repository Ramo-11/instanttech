package dev.omar.registration.endpoints.auth;

import dev.omar.registration.models.user.UserService;
import dev.omar.registration.security.config.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;

    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.username(), request.password());
        var result = authenticationManager.authenticate(authToken);
        System.out.println(result);
        var user = userService.loadUserByUsername(request.username());
        var jwtToken = jwtService.generateToken(user);
        return ResponseEntity.status(200).body(new AuthenticationResponse(jwtToken, "User was authenticated successfully"));
    }
}
