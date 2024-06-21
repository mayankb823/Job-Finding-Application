package com.project.reviewms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Override
    public List<Review> findAll(Long companyId) {
        List<Review> reviews= reviewRepository.findByCompanyId(companyId);
        return reviews;

    }

     @Override
    public Optional<Review> findById(Long reviewId) {

        return  reviewRepository.findById(reviewId);
    }
    @Override
    public boolean deleteById(Long reviewId) {
        reviewRepository.deleteById(reviewId);
        return true;
    }
    @Override
    public Review save(Review review,Long companyId) {
           review.setCompanyId(companyId);
           return reviewRepository.save(review);
    }
}
