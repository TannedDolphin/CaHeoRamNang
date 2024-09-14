package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.dto.request.CreateReviewRequest;
import cayxanh.GreencareTest.entity.Review;
import cayxanh.GreencareTest.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Thêm mới review
    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody CreateReviewRequest request) {
        Review review = reviewService.createReview(request);
        return ResponseEntity.ok(review);
    }

    // Sửa review theo ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Integer id, @RequestBody CreateReviewRequest request) {
        Review updatedReview = reviewService.updateReview(id, request);
        return ResponseEntity.ok(updatedReview);
    }

    // Xóa review theo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Integer id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok("Xóa thành công review với ID: " + id);
    }

    // Lấy danh sách tất cả review
    @GetMapping("/all")
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    // Tìm review theo ID
    @GetMapping("/findById/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Integer id) {
        Review review = reviewService.getReviewById(id);
        return ResponseEntity.ok(review);
    }

    // Tìm review theo sản phẩm (productid)
    @GetMapping("/findByProduct/{productId}")
    public ResponseEntity<List<Review>> getReviewsByProductId(@PathVariable Integer productId) {
        List<Review> reviews = reviewService.getReviewsByProductId(productId);
        return ResponseEntity.ok(reviews);
    }
}
