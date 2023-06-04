package dev.omar.registration.endpoints.root;

import dev.omar.registration.utils.AuthUtils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
@RequiredArgsConstructor
public class RootController {

    private final RootService rootService;
    private final AuthUtils authUtils;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<RootResponse> root() {
        if (authUtils.isAuthenticated()) {
            return rootService.respond();
        }
        return ResponseEntity.status(401).body(new RootResponse("You are not authorized"));
    }
}
