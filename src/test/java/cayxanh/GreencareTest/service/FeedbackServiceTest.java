package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.CreateFeedbackRequest;
import cayxanh.GreencareTest.entity.Feedback;
import cayxanh.GreencareTest.entity.User;
import cayxanh.GreencareTest.repo.FeedbackRepo;
import cayxanh.GreencareTest.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FeedbackServiceTest {

    @Mock
    private FeedbackRepo feedbackRepo;

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private FeedbackService feedbackService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateFeedback() {
        CreateFeedbackRequest request = new CreateFeedbackRequest();
        request.setFeedback("Great service!");
        request.setUserid("user123");

        User user = new User();
        user.setUserid("user123");

        Feedback feedback = new Feedback();
        feedback.setFeedback(request.getFeedback());
        feedback.setUserfeedback(user);

        when(userRepo.findById(request.getUserid())).thenReturn(Optional.of(user));
        when(feedbackRepo.save(any(Feedback.class))).thenReturn(feedback);

        Feedback createdFeedback = feedbackService.createFeedback(request);

        assertNotNull(createdFeedback);
        assertEquals("Great service!", createdFeedback.getFeedback());
        assertEquals("user123", createdFeedback.getUserfeedback().getUserid());
    }

    @Test
    public void testCreateFeedback_UserNotFound() {
        CreateFeedbackRequest request = new CreateFeedbackRequest();
        request.setFeedback("Great service!");
        request.setUserid("user123");

        when(userRepo.findById(request.getUserid())).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            feedbackService.createFeedback(request);
        });
        assertEquals("User không tồn tại với ID: " + request.getUserid(), thrown.getMessage());
    }

    @Test
    public void testUpdateFeedback_Success() {
        Integer feedbackId = 1;
        CreateFeedbackRequest request = new CreateFeedbackRequest();
        request.setFeedback("Updated feedback");
        request.setUserid("user123");

        Feedback existingFeedback = new Feedback();
        existingFeedback.setFeedbackid(feedbackId);
        existingFeedback.setFeedback("Old feedback");

        User user = new User();
        user.setUserid("user123");

        Feedback updatedFeedback = new Feedback();
        updatedFeedback.setFeedbackid(feedbackId);
        updatedFeedback.setFeedback(request.getFeedback());
        updatedFeedback.setUserfeedback(user);

        when(feedbackRepo.findById(feedbackId)).thenReturn(Optional.of(existingFeedback));
        when(userRepo.findById(request.getUserid())).thenReturn(Optional.of(user));
        when(feedbackRepo.save(any(Feedback.class))).thenReturn(updatedFeedback);

        Feedback result = feedbackService.updateFeedback(feedbackId, request);

        assertNotNull(result);
        assertEquals(feedbackId, result.getFeedbackid());
        assertEquals("Updated feedback", result.getFeedback());
        assertEquals("user123", result.getUserfeedback().getUserid());
    }

    @Test
    public void testUpdateFeedback_Failure() {
        Integer feedbackId = 1;
        CreateFeedbackRequest request = new CreateFeedbackRequest();
        request.setFeedback("Updated feedback");
        request.setUserid("user123");

        when(feedbackRepo.findById(feedbackId)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            feedbackService.updateFeedback(feedbackId, request);
        });
        assertEquals("Feedback không tồn tại với ID: " + feedbackId, thrown.getMessage());
    }

    @Test
    public void testDeleteFeedback_Success() {
        Integer feedbackId = 1;

        when(feedbackRepo.existsById(feedbackId)).thenReturn(true);

        feedbackService.deleteFeedback(feedbackId);

        verify(feedbackRepo, times(1)).deleteById(feedbackId);
    }

    @Test
    public void testDeleteFeedback_Failure() {
        Integer feedbackId = 1;

        when(feedbackRepo.existsById(feedbackId)).thenReturn(false);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            feedbackService.deleteFeedback(feedbackId);
        });
        assertEquals("Feedback không tồn tại với ID: " + feedbackId, thrown.getMessage());
    }

    @Test
    public void testGetAllFeedbacks() {
        Feedback feedback1 = new Feedback();
        feedback1.setFeedback("Great service!");

        Feedback feedback2 = new Feedback();
        feedback2.setFeedback("Needs improvement");

        when(feedbackRepo.findAll()).thenReturn(Arrays.asList(feedback1, feedback2));

        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();

        assertNotNull(feedbacks);
        assertEquals(2, feedbacks.size());
        assertEquals("Great service!", feedbacks.get(0).getFeedback());
        assertEquals("Needs improvement", feedbacks.get(1).getFeedback());
    }

    @Test
    public void testGetFeedbackById_Success() {
        Integer feedbackId = 1;

        Feedback feedback = new Feedback();
        feedback.setFeedbackid(feedbackId);
        feedback.setFeedback("Great service!");

        when(feedbackRepo.findById(feedbackId)).thenReturn(Optional.of(feedback));

        Feedback result = feedbackService.getFeedbackById(feedbackId);

        assertNotNull(result);
        assertEquals(feedbackId, result.getFeedbackid());
        assertEquals("Great service!", result.getFeedback());
    }

    @Test
    public void testGetFeedbackById_Failure() {
        Integer feedbackId = 1;

        when(feedbackRepo.findById(feedbackId)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            feedbackService.getFeedbackById(feedbackId);
        });
        assertEquals("Feedback không tồn tại với ID: " + feedbackId, thrown.getMessage());
    }
}
