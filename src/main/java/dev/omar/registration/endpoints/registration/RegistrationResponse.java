package dev.omar.registration.endpoints.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class RegistrationResponse {
    private String token;
    private String message;

    public RegistrationResponse(String message) {
        this.message = message;
    }
}
