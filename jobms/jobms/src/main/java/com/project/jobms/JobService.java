package com.project.jobms;

import com.project.jobms.dto.JobDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface JobService  {

    public List<JobDto> findAll();
    public Optional<Job> findById(Long id);

    public Job save(Job job);
}
