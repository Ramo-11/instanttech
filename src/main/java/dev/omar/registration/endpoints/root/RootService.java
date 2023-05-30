package dev.omar.registration.endpoints.root;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RootService {
    public ResponseEntity<RootResponse> respond() {
        return ResponseEntity.status(200).body(new RootResponse("Salutations!"));
    }
}
