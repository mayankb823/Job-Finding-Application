package com.project.jobms.dto;

import com.project.jobms.Job;
import com.project.jobms.external.Company;
import com.project.jobms.external.Review;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class JobDto {
 private Job job;
 private Company company;


 public Job getJob() {
  return job;
 }

 public List<Review> getReview() {
  return review;
 }

 public void setReview(List<Review> review) {
  this.review = review;
 }

 public void setJob(Job job) {
  this.job = job;
 }

 public Company getCompany() {
  return company;
 }

 public void setCompany(Company company) {
  this.company = company;
 }

 private List<Review> review;
}
