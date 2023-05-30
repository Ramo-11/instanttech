package dev.omar.registration.endpoints.root;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
@RequiredArgsConstructor
public class RootController {

    private final RootService rootService;

    @GetMapping
    public ResponseEntity<RootResponse> root() {
        return rootService.respond();
    }
}
