package dev.omar.registration.endpoints.registration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationResponse {
    private String token;
    private String message;
}
