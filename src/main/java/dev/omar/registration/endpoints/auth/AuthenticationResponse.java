package dev.omar.registration.endpoints.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private String message;

    AuthenticationResponse(String message) {
        this.message = message;
    }
}