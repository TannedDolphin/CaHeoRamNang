package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.entity.Feedback;
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

public class FeedbackControllerTest {

    @Mock
    private FeedbackService feedbackService;

    @InjectMocks
    private FeedbackController feedbackController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllFeedback() {
        List<Feedback> feedbacks = Arrays.asList(new Feedback(), new Feedback());
        when(feedbackService.getFeedback()).thenReturn(feedbacks);

        ResponseEntity<List<Feedback>> response = feedbackController.getAllFeedback();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(feedbacks, response.getBody());
    }

    @Test
    public void testGetFeedbackById() {
        Feedback feedback = new Feedback();
        when(feedbackService.getFeedbackById(1)).thenReturn(feedback);

        ResponseEntity<Feedback> response = feedbackController.getFeedbackById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(feedback, response.getBody());
    }

    @Test
    public void testAddFeedback() {
        Feedback feedback = new Feedback();
        doNothing().when(feedbackService).addFeedback(feedback);

        ResponseEntity<Void> response = feedbackController.addFeedback(feedback);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(feedbackService, times(1)).addFeedback(feedback);
    }

    @Test
    public void testDeleteFeedback() {
        doAnswer(invocation -> {
            // You can add any additional logic here if needed
            return null;
        }).when(feedbackService).deleteFeedback(1);

        ResponseEntity<Void> response = feedbackController.deleteFeedback(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(feedbackService, times(1)).deleteFeedback(1);
    }
}
