package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.entity.Image;
import cayxanh.GreencareTest.repo.ImageRepo;
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

public class ImageServiceTest {

    @Mock
    private ImageRepo imageRepo;

    @InjectMocks
    private ImageService imageService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetListImage() {
        Image image1 = new Image(1L, "image1.jpg", "jpg");
        Image image2 = new Image(2L, "image2.png", "png");

        when(imageRepo.findAll()).thenReturn(Arrays.asList(image1, image2));

        List<Image> images = imageService.getListImage();

        assertNotNull(images);
        assertEquals(2, images.size());
        assertEquals("image1.jpg", images.get(0).getName());
        assertEquals("image2.png", images.get(1).getName());
    }

    @Test
    public void testSave() {
        Image image = new Image(1L, "image1.jpg", "jpg");

        when(imageRepo.save(image)).thenReturn(image);

        Image savedImage = imageService.save(image);

        assertNotNull(savedImage);
        assertEquals("image1.jpg", savedImage.getName());
    }

    @Test
    public void testDeleteImage_Success() {
        Long id = 1L;

        when(imageRepo.existsById(id)).thenReturn(true);

        imageService.deleteImage(id);

        verify(imageRepo, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteImage_Failure() {
        Long id = 1L;

        when(imageRepo.existsById(id)).thenReturn(false);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            imageService.deleteImage(id);
        });
        assertEquals("Image không tồn tại với ID: " + id, thrown.getMessage());
    }

    @Test
    public void testGetImageById_Success() {
        Long id = 1L;
        Image image = new Image(id, "image1.jpg", "jpg");

        when(imageRepo.findById(id)).thenReturn(Optional.of(image));

        Image result = imageService.getImageById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("image1.jpg", result.getName());
    }

    @Test
    public void testGetImageById_Failure() {
        Long id = 1L;

        when(imageRepo.findById(id)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            imageService.getImageById(id);
        });
        assertEquals("Image không tồn tại với ID: " + id, thrown.getMessage());
    }
}
