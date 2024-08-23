package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.entity.Review;
import cayxanh.GreencareTest.repo.ReviewRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewService {
    ReviewRepo reviewRepo;
    public List<Review> getReviews(){
        List<Review> reviews = reviewRepo.findAll();
        if(reviews.isEmpty()){
            throw new RuntimeException("Reviews list is empty");
        }
        return reviews;
    }

    public Review getReview(int id){
        Review review = reviewRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Review not found"));
        return review;
    }

    public Review addReview(Review review){
        Review newReview = reviewRepo.save(review);
        if(newReview == null){
            throw new RuntimeException("Review is null");
        }
        return newReview;
    }

    public Review updateReview(Review review){
        Review updateReview = reviewRepo.findById(review.getReviewid())
                .orElseThrow(()->new RuntimeException("Review not found"));
        updateReview.setReviewrating(review.getReviewrating());
        updateReview.setReviewtext(review.getReviewtext());
        Review updatedReview = reviewRepo.save(updateReview);
        return updatedReview;
    }

    public boolean deleteReview(int id){
        Review review = reviewRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Review not found"));
        reviewRepo.deleteById(review.getReviewid());
        return true;
    }
}
