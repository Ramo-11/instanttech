package dev.omar.registration.endpoints.registration;

import dev.omar.registration.models.user.UserService;
import dev.omar.registration.security.config.JwtService;
import dev.omar.registration.models.user.Role;
import dev.omar.registration.models.user.User;
import dev.omar.registration.utils.EmailValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final EmailValidator emailValidator = new EmailValidator();

    public ResponseEntity<RegistrationResponse> register(RegistrationRequest request) {
        boolean isEmailValid = emailValidator.test(request.username());
        if (!isEmailValid) {
            throw new IllegalStateException("email: " + request.username() + " is not valid");
        }

        User userToSave = new User(request.name(), request.username(), request.password(), Role.FREELANCER);

        userService.signUpUser(userToSave);
        var jwtToken = jwtService.generateToken(userToSave);

        return ResponseEntity.status(200).body(new RegistrationResponse(jwtToken, "User was registered successfully"));
    }
}
