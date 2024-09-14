package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.CreateFeedbackRequest;
import cayxanh.GreencareTest.entity.Feedback;
import cayxanh.GreencareTest.entity.User;
import cayxanh.GreencareTest.repo.FeedbackRepo;
import cayxanh.GreencareTest.repo.UserRepo; // Giả định bạn có repo cho User
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepo feedbackRepo;

    @Autowired
    private UserRepo userRepo; // Giả định bạn có repo cho User

    public Feedback createFeedback(CreateFeedbackRequest request) {
        Feedback feedback = new Feedback();
        feedback.setFeedback(request.getFeedback());

        User user = userRepo.findById(request.getUserid())
                .orElseThrow(() -> new RuntimeException("User không tồn tại với ID: " + request.getUserid()));
        feedback.setUserfeedback(user);

        return feedbackRepo.save(feedback);
    }

    public Feedback updateFeedback(Integer id, CreateFeedbackRequest request) {
        Feedback feedback = feedbackRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback không tồn tại với ID: " + id));

        feedback.setFeedback(request.getFeedback());

        User user = userRepo.findById(request.getUserid())
                .orElseThrow(() -> new RuntimeException("User không tồn tại với ID: " + request.getUserid()));
        feedback.setUserfeedback(user);

        return feedbackRepo.save(feedback);
    }

    public void deleteFeedback(Integer id) {
        if (!feedbackRepo.existsById(id)) {
            throw new RuntimeException("Feedback không tồn tại với ID: " + id);
        }
        feedbackRepo.deleteById(id);
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepo.findAll();
    }

    public Feedback getFeedbackById(Integer id) {
        return feedbackRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback không tồn tại với ID: " + id));
    }

}
