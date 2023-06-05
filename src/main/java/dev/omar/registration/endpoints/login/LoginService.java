package dev.omar.registration.endpoints.login;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
//    private final UserService userService;
//    private final JwtService jwtService;

    public ResponseEntity<LoginResponse> login(LoginRequest request) {
        return ResponseEntity.status(200).body(new LoginResponse("User was logged successfully"));
    }
}
