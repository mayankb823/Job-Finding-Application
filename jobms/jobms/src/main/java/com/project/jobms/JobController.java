package com.project.jobms;

import com.project.jobms.dto.JobDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;
    @Autowired
    private JobApplicationService service;

    @GetMapping
    @CircuitBreaker(name="CompanyandRatingBreaker",fallbackMethod = "comapanyRatingFallback")
    public ResponseEntity<List<JobDto>> getAllJobs() {
        List<JobDto> jobs = jobService.findAll();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    public ResponseEntity<List<JobDto>> comapanyRatingFallback(Exception ex){
        List<JobDto> jobs=new ArrayList<>();
        jobs.add(null);

        return new ResponseEntity<>(jobs,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Optional<Job> job = jobService.findById(id);
        return job.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found with id: " + id)
                );
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        Job createdJob = jobService.save(job);
        return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
    }

    @PostMapping("/apply/{jobId}")
    public ResponseEntity<String> applyJob(@PathVariable Long jobId,
                                           @RequestHeader(value = "X-User-Id", required = false) Long userId,
                                           @RequestHeader(value = "X-User-Email", required = false) String email,
                                           @RequestBody(required = false) JobApplyRequest request) {
        System.out.println("USER ID = " + userId);
        String resumeUrl = null;

        if(request != null){
            resumeUrl = request.getResumeUrl();
        }
        System.out.println("email = "+email);
        if(email == null){
            email="bmayank854@gmail.com";
        }
        System.out.println("email = "+email);
        return ResponseEntity.ok(service.applyJob(jobId, userId,email,resumeUrl));
    }
}
