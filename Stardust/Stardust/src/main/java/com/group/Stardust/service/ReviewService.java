package com.group.Stardust.service;

import com.group.Stardust.models.Review;
import com.group.Stardust.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository repository;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public List<Review> getReviewsByGame(String gameTitle) {
        return repository.findByGameTitle(gameTitle);
    }

    public void saveReview(Review review) {
        repository.save(review);
    }

    public double getAverageRating(String gameTitle) {
        List<Review> reviews = getReviewsByGame(gameTitle);
        if (reviews.isEmpty()) return 0;

        double total = reviews.stream().mapToInt(Review::getRating).sum();
        return total / reviews.size();
    }
}
