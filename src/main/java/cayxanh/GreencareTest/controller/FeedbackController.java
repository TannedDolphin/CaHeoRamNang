package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.entity.Feedback;
import cayxanh.GreencareTest.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    // Lấy danh sách tất cả phản hồi
    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedback() {
        List<Feedback> feedbacks = feedbackService.getFeedback();
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }

    // Lấy phản hồi theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable int id) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }

    // Thêm phản hồi mới
    @PostMapping
    public ResponseEntity<Void> addFeedback(@RequestBody Feedback feedback) {
        feedbackService.addFeedback(feedback);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Xóa phản hồi
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable int id) {
        feedbackService.deleteFeedback(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
