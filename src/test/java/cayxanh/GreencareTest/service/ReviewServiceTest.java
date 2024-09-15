package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.CreateReviewRequest;
import cayxanh.GreencareTest.entity.Product;
import cayxanh.GreencareTest.entity.Review;
import cayxanh.GreencareTest.entity.User;
import cayxanh.GreencareTest.repo.ProductRepo;
import cayxanh.GreencareTest.repo.ReviewRepo;
import cayxanh.GreencareTest.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ReviewServiceTest {

    @Mock
    private ReviewRepo reviewRepo;

    @Mock
    private ProductRepo productRepo;

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private ReviewService reviewService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateReview_Success() {
        // Arrange
        Integer productId = 1;
        String userId = "user123";
        Product product = new Product();  // Assuming Product has a default constructor
        User user = new User();  // Assuming User has a default constructor

        CreateReviewRequest request = new CreateReviewRequest();
        request.setProductid(productId);
        request.setUserid(userId);
        request.setReviewrating(5);
        request.setReviewtext("Excellent!");

        when(productRepo.findById(productId)).thenReturn(Optional.of(product));
        when(userRepo.findById(userId)).thenReturn(Optional.of(user));
        when(reviewRepo.save(any(Review.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Review createdReview = reviewService.createReview(request);

        // Assert
        assertNotNull(createdReview);
        assertEquals(5, createdReview.getReviewrating());
        assertEquals("Excellent!", createdReview.getReviewtext());
        assertEquals(product, createdReview.getProductreview());
    }

    @Test
    public void testCreateReview_ProductNotFound() {
        // Arrange
        Integer productId = 1;
        String userId = "user123";
        CreateReviewRequest request = new CreateReviewRequest();
        request.setProductid(productId);
        request.setUserid(userId);
        request.setReviewrating(5);
        request.setReviewtext("Excellent!");

        when(productRepo.findById(productId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            reviewService.createReview(request);
        });
        assertEquals("Product không tồn tại với ID: 1", thrown.getMessage());
    }

    @Test
    public void testCreateReview_UserNotFound() {
        // Arrange
        Integer productId = 1;
        String userId = "user123";
        Product product = new Product();  // Assuming Product has a default constructor

        CreateReviewRequest request = new CreateReviewRequest();
        request.setProductid(productId);
        request.setUserid(userId);
        request.setReviewrating(5);
        request.setReviewtext("Excellent!");

        when(productRepo.findById(productId)).thenReturn(Optional.of(product));
        when(userRepo.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            reviewService.createReview(request);
        });
        assertEquals("User không tồn tại với ID: user123", thrown.getMessage());
    }

    @Test
    public void testUpdateReview_Success() {
        // Arrange
        Integer reviewId = 1;
        CreateReviewRequest request = new CreateReviewRequest();
        request.setReviewrating(4);
        request.setReviewtext("Updated review text");

        Review existingReview = new Review();
        existingReview.setReviewid(reviewId);
        existingReview.setReviewrating(3);
        existingReview.setReviewtext("Old review text");

        when(reviewRepo.findById(reviewId)).thenReturn(Optional.of(existingReview));
        when(reviewRepo.save(any(Review.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Review updatedReview = reviewService.updateReview(reviewId, request);

        // Assert
        assertNotNull(updatedReview);
        assertEquals(4, updatedReview.getReviewrating());
        assertEquals("Updated review text", updatedReview.getReviewtext());
    }

    @Test
    public void testUpdateReview_NotFound() {
        // Arrange
        Integer reviewId = 1;
        CreateReviewRequest request = new CreateReviewRequest();
        request.setReviewrating(4);
        request.setReviewtext("Updated review text");

        when(reviewRepo.findById(reviewId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            reviewService.updateReview(reviewId, request);
        });
        assertEquals("Review không tồn tại với ID: 1", thrown.getMessage());
    }

    @Test
    public void testDeleteReview_Success() {
        // Arrange
        Integer reviewId = 1;

        when(reviewRepo.existsById(reviewId)).thenReturn(true);

        // Act
        reviewService.deleteReview(reviewId);

        // Assert
        verify(reviewRepo, times(1)).deleteById(reviewId);
    }

    @Test
    public void testDeleteReview_NotFound() {
        // Arrange
        Integer reviewId = 1;

        when(reviewRepo.existsById(reviewId)).thenReturn(false);

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            reviewService.deleteReview(reviewId);
        });
        assertEquals("Review không tồn tại với ID: 1", thrown.getMessage());
    }

    @Test
    public void testGetAllReviews() {
        // Arrange
        Review review = new Review();
        review.setReviewid(1);
        review.setReviewrating(5);
        review.setReviewtext("Great product!");

        when(reviewRepo.findAll()).thenReturn(Collections.singletonList(review));

        // Act
        List<Review> reviews = reviewService.getAllReviews();

        // Assert
        assertNotNull(reviews);
        assertEquals(1, reviews.size());
        assertEquals(1, reviews.get(0).getReviewid());
    }

    @Test
    public void testGetReviewById_Success() {
        // Arrange
        Integer reviewId = 1;
        Review review = new Review();
        review.setReviewid(reviewId);
        review.setReviewrating(5);
        review.setReviewtext("Great product!");

        when(reviewRepo.findById(reviewId)).thenReturn(Optional.of(review));

        // Act
        Review foundReview = reviewService.getReviewById(reviewId);

        // Assert
        assertNotNull(foundReview);
        assertEquals(reviewId, foundReview.getReviewid());
    }

    @Test
    public void testGetReviewById_NotFound() {
        // Arrange
        Integer reviewId = 1;

        when(reviewRepo.findById(reviewId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            reviewService.getReviewById(reviewId);
        });
        assertEquals("Review không tồn tại với ID: 1", thrown.getMessage());
    }

    @Test
    public void testGetReviewsByProductId_Success() {
        // Arrange
        Integer productId = 1;
        Product product = new Product();  // Assuming Product has a default constructor
        Review review = new Review();
        review.setReviewid(1);
        review.setReviewrating(5);
        review.setReviewtext("Great product!");

        product.setReviews(Collections.singletonList(review));

        when(productRepo.findById(productId)).thenReturn(Optional.of(product));

        // Act
        List<Review> reviews = reviewService.getReviewsByProductId(productId);

        // Assert
        assertNotNull(reviews);
        assertEquals(1, reviews.size());
        assertEquals(5, reviews.get(0).getReviewrating());
    }

    @Test
    public void testGetReviewsByProductId_ProductNotFound() {
        // Arrange
        Integer productId = 1;

        when(productRepo.findById(productId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            reviewService.getReviewsByProductId(productId);
        });
        assertEquals("Product không tồn tại với ID: 1", thrown.getMessage());
    }
}
