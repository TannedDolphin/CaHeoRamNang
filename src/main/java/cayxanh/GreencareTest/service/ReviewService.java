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
    public List<Review> getReviews(){ return reviewRepo.findAll(); }
}
