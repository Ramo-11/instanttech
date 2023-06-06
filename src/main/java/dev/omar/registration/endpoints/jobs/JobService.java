package dev.omar.registration.endpoints.jobs;

import dev.omar.registration.entities.job.Job;
import dev.omar.registration.entities.job.JobRepository;
import dev.omar.registration.entities.user.Role;
import dev.omar.registration.entities.user.User;
import dev.omar.registration.entities.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobService {
    private final UserService userService;
    private final JobRepository jobRepository;

    public ResponseEntity<JobResponse> createJob(JobRequest request, User createdBy) {
        try {
            jobRepository.save(new Job(request.title(), request.description(), request.rate(), request.date(), createdBy));
            return ResponseEntity.status(HttpStatus.OK).body(new JobResponse("Job was created successfully"));
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JobResponse("Error, check logs"));
        }
    }
}
