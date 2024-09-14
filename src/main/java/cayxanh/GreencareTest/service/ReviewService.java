package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.CreateReviewRequest;
import cayxanh.GreencareTest.entity.Product;
import cayxanh.GreencareTest.entity.Review;
import cayxanh.GreencareTest.entity.User;
import cayxanh.GreencareTest.repo.ProductRepo;
import cayxanh.GreencareTest.repo.ReviewRepo;
import cayxanh.GreencareTest.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;

    // Thêm mới review
    public Review createReview(CreateReviewRequest request) {
        // Kiểm tra xem sản phẩm và người dùng có tồn tại không
        Optional<Product> product = productRepo.findById(request.getProductid());
        Optional<User> user = userRepo.findById(request.getUserid());

        if (product.isEmpty()) {
            throw new RuntimeException("Product không tồn tại với ID: " + request.getProductid());
        }
        if (user.isEmpty()) {
            throw new RuntimeException("User không tồn tại với ID: " + request.getUserid());
        }

        // Tạo mới review
        Review review = new Review();
        review.setReviewrating(request.getReviewrating());
        review.setReviewtext(request.getReviewtext());
        review.setProductreview(product.get());
        review.setUserreview(user.get());

        return reviewRepo.save(review);
    }

    // Sửa review theo ID
    public Review updateReview(Integer id, CreateReviewRequest request) {
        Optional<Review> reviewOptional = reviewRepo.findById(id);

        if (reviewOptional.isPresent()) {
            Review review = reviewOptional.get();
            review.setReviewrating(request.getReviewrating());
            review.setReviewtext(request.getReviewtext());
            return reviewRepo.save(review);
        } else {
            throw new RuntimeException("Review không tồn tại với ID: " + id);
        }
    }

    // Xóa review theo ID
    public void deleteReview(Integer id) {
        if (reviewRepo.existsById(id)) {
            reviewRepo.deleteById(id);
        } else {
            throw new RuntimeException("Review không tồn tại với ID: " + id);
        }
    }

    // Lấy danh sách tất cả review
    public List<Review> getAllReviews() {
        return reviewRepo.findAll();
    }

    // Tìm review theo ID
    public Review getReviewById(Integer id) {
        return reviewRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Review không tồn tại với ID: " + id));
    }

    // Tìm review theo sản phẩm (productid)
    public List<Review> getReviewsByProductId(Integer productId) {
        Optional<Product> product = productRepo.findById(productId);
        if (product.isPresent()) {
            return product.get().getReviews();
        } else {
            throw new RuntimeException("Product không tồn tại với ID: " + productId);
        }
    }
}
