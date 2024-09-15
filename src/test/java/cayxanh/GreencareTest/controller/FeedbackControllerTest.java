package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.dto.request.CreateFeedbackRequest;
import cayxanh.GreencareTest.entity.Feedback;
import cayxanh.GreencareTest.service.FeedbackService;
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
    public void testCreateFeedback() {
        CreateFeedbackRequest request = new CreateFeedbackRequest();
        Feedback feedback = new Feedback();
        when(feedbackService.createFeedback(any(CreateFeedbackRequest.class))).thenReturn(feedback);

        ResponseEntity<Feedback> response = feedbackController.createFeedback(request);

        assertEquals(feedback, response.getBody());
        verify(feedbackService, times(1)).createFeedback(any(CreateFeedbackRequest.class));
    }

    @Test
    public void testUpdateFeedback() {
        CreateFeedbackRequest request = new CreateFeedbackRequest();
        Feedback feedback = new Feedback();
        when(feedbackService.updateFeedback(anyInt(), any(CreateFeedbackRequest.class))).thenReturn(feedback);

        ResponseEntity<Feedback> response = feedbackController.updateFeedback(1, request);

        assertEquals(feedback, response.getBody());
        verify(feedbackService, times(1)).updateFeedback(anyInt(), any(CreateFeedbackRequest.class));
    }

    @Test
    public void testDeleteFeedback() {
        doNothing().when(feedbackService).deleteFeedback(anyInt());

        ResponseEntity<Void> response = feedbackController.deleteFeedback(1);

        assertEquals(204, response.getStatusCodeValue());
        verify(feedbackService, times(1)).deleteFeedback(anyInt());
    }

    @Test
    public void testGetAllFeedbacks() {
        List<Feedback> feedbacks = Arrays.asList(new Feedback(), new Feedback());
        when(feedbackService.getAllFeedbacks()).thenReturn(feedbacks);

        ResponseEntity<List<Feedback>> response = feedbackController.getAllFeedbacks();

        assertEquals(feedbacks, response.getBody());
        verify(feedbackService, times(1)).getAllFeedbacks();
    }

    @Test
    public void testGetFeedbackById() {
        Feedback feedback = new Feedback();
        when(feedbackService.getFeedbackById(anyInt())).thenReturn(feedback);

        ResponseEntity<Feedback> response = feedbackController.getFeedbackById(1);

        assertEquals(feedback, response.getBody());
        verify(feedbackService, times(1)).getFeedbackById(anyInt());
    }
}
