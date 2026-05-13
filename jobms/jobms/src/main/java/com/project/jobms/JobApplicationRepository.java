package com.project.jobms;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {

    boolean existsByJobIdAndUserId(Long jobId, Long userId);

    List<JobApplication> findByUserId(Long userId);
}