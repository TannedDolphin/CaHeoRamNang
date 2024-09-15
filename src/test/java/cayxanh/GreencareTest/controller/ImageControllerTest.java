package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.entity.Image;
import cayxanh.GreencareTest.service.ImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ImageControllerTest {

    @Mock
    private ImageService imageService;

    @InjectMocks
    private ImageController imageController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetList() {
        List<Image> images = Arrays.asList(new Image(), new Image());
        when(imageService.getListImage()).thenReturn(images);

        ResponseEntity<List<Image>> response = imageController.getList();

        assertEquals(images, response.getBody());
        verify(imageService, times(1)).getListImage();
    }

    @Test
    public void testGetImageById() {
        Image image = new Image();
        when(imageService.getImageById(anyLong())).thenReturn(image);

        ResponseEntity<Image> response = imageController.getImageById(1L);

        assertEquals(image, response.getBody());
        verify(imageService, times(1)).getImageById(anyLong());
    }

    @Test
    public void testUploadFile() {
        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "test image content".getBytes());
        Image image = new Image();
        image.setName("test.jpg");
        image.setType("jpg");

        when(imageService.save(any(Image.class))).thenReturn(image);

        ResponseEntity<Image> response = imageController.uploadFile(file);

        assertEquals(image, response.getBody());
        verify(imageService, times(1)).save(any(Image.class));
    }

    @Test
    public void testDeleteImage() {
        doNothing().when(imageService).deleteImage(anyLong());

        ResponseEntity<String> response = imageController.deleteImage(1L);

        assertEquals("Xóa thành công ảnh với ID: 1", response.getBody());
        verify(imageService, times(1)).deleteImage(anyLong());
    }
}
