package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.entity.Image;
import cayxanh.GreencareTest.repo.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepo imageRepo;

    // Lấy danh sách tất cả các ảnh
    public List<Image> getListImage() {
        return imageRepo.findAll();
    }

    // Lưu ảnh
    public Image save(Image image) {
        return imageRepo.save(image);
    }

    // Xóa ảnh theo ID
    public void deleteImage(Long id) {
        if (imageRepo.existsById(id)) {
            imageRepo.deleteById(id);
        } else {
            throw new RuntimeException("Image không tồn tại với ID: " + id);
        }
    }

    // Lấy ảnh theo ID
    public Image getImageById(Long id) {
        return imageRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Image không tồn tại với ID: " + id));
    }
}
