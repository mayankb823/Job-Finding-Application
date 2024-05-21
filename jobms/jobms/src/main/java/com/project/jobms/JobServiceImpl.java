package com.project.jobms;
import com.project.jobms.config.CompanyClient;
import com.project.jobms.dto.JobDto;
import com.project.jobms.external.Company;
import com.project.jobms.external.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
@Service
public class JobServiceImpl implements JobService{
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private RestTemplate restTemplate;
     @Autowired
    CompanyClient companyClient;

    public JobDto convertToDto(Job job){
          Company company =companyClient.getCompany(job.getCompanyId());
        ResponseEntity<List<Review>> reviewResponse=restTemplate.exchange("http://REVIEW-SERVICE:8083/reviews?companyId="+job.getCompanyId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Review>>() {
                }
        );
        List<Review> review=reviewResponse.getBody();
        JobDto jobDto=new JobDto();
        jobDto.setJob(job);
        jobDto.setCompany(company);
        jobDto.setReview(review);

        return jobDto;
    }

    @Override
    public List<JobDto> findAll() {
        List<Job> jobs= jobRepository.findAll();
        List < JobDto > jobDtos = new ArrayList<>();
        for(Job job:jobs) {
            JobDto jobDto=  convertToDto(job);
            jobDtos.add(jobDto);
            }
        return jobDtos;
    }
    @Override
    public Optional<Job> findById(Long id) {
        return jobRepository.findById(id);
    }
    @Override
    public Job save(Job job) {
        return jobRepository.save(job);
    }
}
