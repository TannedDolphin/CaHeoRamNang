package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.entity.Review;
import cayxanh.GreencareTest.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReviewControllerTest {

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllReviews() {
        List<Review> reviews = Arrays.asList(new Review(), new Review());
        when(reviewService.getReviews()).thenReturn(reviews);

        ResponseEntity<List<Review>> response = reviewController.getAllReviews();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reviews, response.getBody());
    }

    @Test
    public void testGetReviewById() {
        Review review = new Review();
        when(reviewService.getReview(1)).thenReturn(review);

        ResponseEntity<Review> response = reviewController.getReviewById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(review, response.getBody());
    }

    @Test
    public void testAddReview() {
        Review review = new Review();
        when(reviewService.addReview(review)).thenReturn(review);

        ResponseEntity<Review> response = reviewController.addReview(review);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(review, response.getBody());
    }

    @Test
    public void testUpdateReview() {
        Review review = new Review();
        review.setReviewid(1);
        when(reviewService.updateReview(review)).thenReturn(review);

        ResponseEntity<Review> response = reviewController.updateReview(1, review);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(review, response.getBody());
    }

    @Test
    public void testDeleteReview() {
        doAnswer(invocation -> {
            // You can add any additional logic here if needed
            return null;
        }).when(reviewService).deleteReview(1);

        ResponseEntity<Void> response = reviewController.deleteReview(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(reviewService, times(1)).deleteReview(1);
    }
}
