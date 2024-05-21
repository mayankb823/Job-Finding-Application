package com.project.reviewms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serial;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId) {
        List<Review> reviews = reviewService.findAll(companyId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Optional<Review>> getReviewById(@PathVariable Long reviewId) {
        Optional<Review> review = reviewService.findById(reviewId);
        return new ResponseEntity<>(review,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review,@RequestParam Long companyId)  {
        Review createdReview = reviewService.save(review,companyId);//Add Review
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

}
