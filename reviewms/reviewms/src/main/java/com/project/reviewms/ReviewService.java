package com.project.reviewms;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

     List<Review> findAll(Long companyId);

     Optional<Review> findById(Long reviewId);
    boolean deleteById(Long reviewId);

    Review save(Review review,Long companyId);
}
