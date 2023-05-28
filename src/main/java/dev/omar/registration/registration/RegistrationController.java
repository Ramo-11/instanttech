package dev.omar.registration.registration;

import dev.omar.registration.auth.AuthenticationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/register", consumes = {"application/x-www-form-urlencoded", "text/plain", "application/*"})
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    // TODO: Change AuthenticationResponse to RegistrationResponse
    @PostMapping
    public AuthenticationResponse register(RegistrationRequest request) {
        return registrationService.register(request);
    }

}
