package dev.omar.registration.root;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RootService {
    public ResponseEntity<RootResponse> sayHello() {
        return ResponseEntity.status(200).body(new RootResponse("Hello"));
    }
}
