package dev.omar.registration.endpoints.registration;

import dev.omar.registration.entities.user.UserService;
import dev.omar.registration.security.config.JwtService;
import dev.omar.registration.entities.user.Role;
import dev.omar.registration.entities.user.User;
import dev.omar.registration.utils.EmailValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
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
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new RegistrationResponse("Email '" + request.username() + "' is invalid"));
        }

        Role role;
        if (request.role().equalsIgnoreCase(Role.FREELANCER.name())) {
            role = Role.FREELANCER;
        } else {
            role = Role.CLIENT;
        }
        User userToSave = new User(request.name(), request.username(), request.password(), role);

        Pair<Integer, String> pairResponse = userService.createUserAccount(userToSave);
        RegistrationResponse response = new RegistrationResponse(pairResponse.getSecond());
        if (pairResponse.getFirst() == 200) {
            var jwtToken = jwtService.generateToken(userToSave);
            response.setToken(jwtToken);
        }

        return ResponseEntity.status(pairResponse.getFirst()).body(response);
    }
}
