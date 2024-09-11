package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.entity.Review;
import cayxanh.GreencareTest.repo.ReviewRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    private ReviewRepo reviewRepo;

    @InjectMocks
    private ReviewService reviewService;

    private Review review;

    @BeforeEach
    void setUp() {
        review = new Review();
        review.setReviewid(1);
        review.setReviewrating(5);
        review.setReviewtext("Great product!");
    }

    @Test
    void testGetReviews() {
        when(reviewRepo.findAll()).thenReturn(Arrays.asList(review));

        List<Review> reviewsList = reviewService.getReviews();
        assertFalse(reviewsList.isEmpty());
        assertEquals(1, reviewsList.size());
        verify(reviewRepo, times(1)).findAll();
    }

    @Test
    void testGetReview() {
        when(reviewRepo.findById(1)).thenReturn(Optional.of(review));

        Review foundReview = reviewService.getReview(1);
        assertNotNull(foundReview);
        verify(reviewRepo, times(1)).findById(1);
    }

    @Test
    void testAddReview() {
        when(reviewRepo.save(any(Review.class))).thenReturn(review);

        Review newReview = reviewService.addReview(review);
        assertNotNull(newReview);
        assertEquals("Great product!", newReview.getReviewtext());
        verify(reviewRepo, times(1)).save(review);
    }

    @Test
    void testUpdateReview() {
        when(reviewRepo.findById(review.getReviewid())).thenReturn(Optional.of(review));
        when(reviewRepo.save(any(Review.class))).thenReturn(review);

        Review updatedReview = reviewService.updateReview(review);
        assertNotNull(updatedReview);
        assertEquals(5, updatedReview.getReviewrating());
        assertEquals("Great product!", updatedReview.getReviewtext());
        verify(reviewRepo, times(1)).findById(review.getReviewid());
        verify(reviewRepo, times(1)).save(review);
    }

    @Test
    void testDeleteReview() {
        when(reviewRepo.findById(1)).thenReturn(Optional.of(review));

        boolean isDeleted = reviewService.deleteReview(1);
        assertTrue(isDeleted);
        verify(reviewRepo, times(1)).findById(1);
        verify(reviewRepo, times(1)).deleteById(1);
    }
}
