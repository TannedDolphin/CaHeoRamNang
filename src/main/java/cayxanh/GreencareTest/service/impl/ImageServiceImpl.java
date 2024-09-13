package cayxanh.GreencareTest.service.impl;

import cayxanh.GreencareTest.exception.NotFoundException;
import cayxanh.GreencareTest.repo.ImageRepository;
import cayxanh.GreencareTest.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cayxanh.GreencareTest.entity.Image;


import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Image> getListImage() {
        // TODO Auto-generated method stub
        return imageRepository.findAll();
    }

    @Override
    public Image getImageById(long id) {
        // TODO Auto-generated method stub
        Image image = imageRepository.findById(id).orElseThrow(() -> new NotFoundException("Image not found width id :" + id));

        return image;
    }

    @Override
    public Image save(Image image) {
        // TODO Auto-generated method stub
        return imageRepository.save(image);
    }

    @Override
    public void deleteImage(long id) {
        // TODO Auto-generated method stub

    }
}
