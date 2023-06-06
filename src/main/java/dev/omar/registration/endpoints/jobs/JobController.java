package dev.omar.registration.endpoints.jobs;

import dev.omar.registration.endpoints.root.RootResponse;
import dev.omar.registration.entities.user.User;
import dev.omar.registration.utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/jobs")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;
    private final AuthUtils authUtils;

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<JobResponse> createJob(@RequestBody JobRequest request, @AuthenticationPrincipal User user) {
        if (authUtils.isAuthenticated()) {
            return jobService.createJob(request, user);
        }
        return ResponseEntity.status(401).body(new JobResponse("You are not authorized"));
    }
}
