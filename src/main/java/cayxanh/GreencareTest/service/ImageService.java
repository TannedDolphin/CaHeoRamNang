package cayxanh.GreencareTest.service;

import java.util.List;
import cayxanh.GreencareTest.entity.Image;

public interface ImageService {
    List<Image> getListImage();

    Image getImageById(long id);

    Image save(Image image);

    void deleteImage(long id);
}
