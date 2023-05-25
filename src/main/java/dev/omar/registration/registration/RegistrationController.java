package dev.omar.registration.registration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/register")
public class RegistrationController {

    private final RegistrationService registrationService = new RegistrationService();

    @GetMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

}
