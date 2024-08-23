package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.entity.Feedback;
import cayxanh.GreencareTest.repo.FeedbackRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FeedbackService {
    FeedbackRepo feedbackRepo;
    public List<Feedback> getFeedback() {
        List<Feedback> feedbacks = feedbackRepo.findAll();
        if (feedbacks.isEmpty()) {
            throw new RuntimeException("Feedback List is empty");
        }
        return feedbacks;
    }
    public Feedback getFeedbackById(int id) {
        Feedback feedback = feedbackRepo.findById(id).orElseThrow(() -> new RuntimeException("Feedback not found"));
        return feedback;
    }
    public void addFeedback(Feedback feedback) {
        Feedback newFeedback = feedbackRepo.save(feedback);
        if (newFeedback == null) {
            throw new RuntimeException("Feedback is null");
        }
    }
    public boolean deleteFeedback(int id) {
        Feedback feedback1= feedbackRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Feedback not found"));
        feedbackRepo.deleteById(feedback1.getFeedbackid());
        return true;
    }
}
