package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.entity.Feedback;
import cayxanh.GreencareTest.repo.FeedbackRepo;
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

class FeedbackServiceTest {

    @Mock
    private FeedbackRepo feedbackRepo;

    @InjectMocks
    private FeedbackService feedbackService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFeedback() {
        Feedback feedback1 = new Feedback();
        Feedback feedback2 = new Feedback();
        when(feedbackRepo.findAll()).thenReturn(Arrays.asList(feedback1, feedback2));

        List<Feedback> feedbacks = feedbackService.getFeedback();

        assertNotNull(feedbacks);
        assertEquals(2, feedbacks.size());
    }

    @Test
    void testGetFeedbackById() {
        Feedback feedback = new Feedback();
        when(feedbackRepo.findById(1)).thenReturn(Optional.of(feedback));

        Feedback result = feedbackService.getFeedbackById(1);

        assertNotNull(result);
    }

    @Test
    void testAddFeedback() {
        Feedback feedback = new Feedback();
        when(feedbackRepo.save(feedback)).thenReturn(feedback);

        feedbackService.addFeedback(feedback);

        verify(feedbackRepo, times(1)).save(feedback);
    }

    @Test
    void testDeleteFeedback() {
        Feedback feedback = new Feedback();
        feedback.setFeedbackid(1);
        when(feedbackRepo.findById(1)).thenReturn(Optional.of(feedback));

        boolean result = feedbackService.deleteFeedback(1);

        assertTrue(result);
        verify(feedbackRepo, times(1)).deleteById(1);
    }

    @Test
    void testGetFeedbackByIdWithAdminRole() {
        Feedback feedback = new Feedback();
        when(feedbackRepo.findById(1)).thenReturn(Optional.of(feedback));

        Feedback result = feedbackService.getFeedbackById(1);

        assertNotNull(result);
    }
}
