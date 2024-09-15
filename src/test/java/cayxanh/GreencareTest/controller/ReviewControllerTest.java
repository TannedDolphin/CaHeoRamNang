package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.dto.request.CreateReviewRequest;
import cayxanh.GreencareTest.entity.Review;
import cayxanh.GreencareTest.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
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
    public void testCreateReview() {
        CreateReviewRequest request = new CreateReviewRequest();
        Review review = new Review();
        when(reviewService.createReview(any(CreateReviewRequest.class))).thenReturn(review);

        ResponseEntity<Review> response = reviewController.createReview(request);

        assertEquals(review, response.getBody());
        verify(reviewService, times(1)).createReview(any(CreateReviewRequest.class));
    }

    @Test
    public void testUpdateReview() {
        CreateReviewRequest request = new CreateReviewRequest();
        Review review = new Review();
        when(reviewService.updateReview(anyInt(), any(CreateReviewRequest.class))).thenReturn(review);

        ResponseEntity<Review> response = reviewController.updateReview(1, request);

        assertEquals(review, response.getBody());
        verify(reviewService, times(1)).updateReview(anyInt(), any(CreateReviewRequest.class));
    }

    @Test
    public void testDeleteReview() {
        doNothing().when(reviewService).deleteReview(anyInt());

        ResponseEntity<String> response = reviewController.deleteReview(1);

        assertEquals("Xóa thành công review với ID: 1", response.getBody());
        verify(reviewService, times(1)).deleteReview(anyInt());
    }

    @Test
    public void testGetAllReviews() {
        List<Review> reviews = Arrays.asList(new Review(), new Review());
        when(reviewService.getAllReviews()).thenReturn(reviews);

        ResponseEntity<List<Review>> response = reviewController.getAllReviews();

        assertEquals(reviews, response.getBody());
        verify(reviewService, times(1)).getAllReviews();
    }

    @Test
    public void testGetReviewById() {
        Review review = new Review();
        when(reviewService.getReviewById(anyInt())).thenReturn(review);

        ResponseEntity<Review> response = reviewController.getReviewById(1);

        assertEquals(review, response.getBody());
        verify(reviewService, times(1)).getReviewById(anyInt());
    }

    @Test
    public void testGetReviewsByProductId() {
        List<Review> reviews = Arrays.asList(new Review(), new Review());
        when(reviewService.getReviewsByProductId(anyInt())).thenReturn(reviews);

        ResponseEntity<List<Review>> response = reviewController.getReviewsByProductId(1);

        assertEquals(reviews, response.getBody());
        verify(reviewService, times(1)).getReviewsByProductId(anyInt());
    }
}
