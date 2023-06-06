package dev.omar.registration.endpoints.registration;

public record RegistrationRequest(String name, String password, String username, String role) {
}
