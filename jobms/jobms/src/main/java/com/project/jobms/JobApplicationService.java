package com.project.jobms;

import com.project.jobms.Job;
import com.project.jobms.JobRepository;
import com.project.jobms.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository repository;

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    KafkaProducerService kafkaProducerService;

    public String applyJob(Long jobId,
                           Long userId,
                           String email,
                           String resumeUrl) {

        // ✅ Check job exists
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job not found with id: " + jobId
                        )
                );

        // ✅ Prevent duplicate apply
        if (repository.existsByJobIdAndUserId(jobId, userId)) {
            throw new RuntimeException(
                    "You already applied for this job"
            );
        }

        // ✅ Save application
        JobApplication application =
                JobApplication.builder()
                        .jobId(jobId)
                        .userId(userId)
                        .resumeUrl(resumeUrl)
                        .status("APPLIED")
                        .appliedAt(LocalDateTime.now())
                        .build();

        repository.save(application);
        JobAppliedEvent event =
                new JobAppliedEvent(
                        userId,
                        email,
                        jobId,
                        job.getTitle()
                );


        kafkaProducerService.sendEvent(event);

        return "Job Applied Successfully";
    }
}