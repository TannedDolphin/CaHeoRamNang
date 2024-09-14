package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.entity.Image;
import cayxanh.GreencareTest.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ImageController {

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/images/";

    @Autowired
    private ImageService imageService;

    // Lấy danh sách tất cả ảnh
    @GetMapping("/all")
    public ResponseEntity<List<Image>> getList() {
        List<Image> listImage = imageService.getListImage();
        return ResponseEntity.ok(listImage);
    }

    // Lấy ảnh theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Image> getImageById(@PathVariable Long id) {
        Image image = imageService.getImageById(id);
        return ResponseEntity.ok(image);
    }

    // Tải lên file ảnh
    @PostMapping("/upload")
    public ResponseEntity<Image> uploadFile(@RequestParam("file") MultipartFile file) {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

        if (originalFilename != null && originalFilename.length() > 0) {
            if (!extension.equalsIgnoreCase("png") && !extension.equalsIgnoreCase("jpg")
                    && !extension.equalsIgnoreCase("gif") && !extension.equalsIgnoreCase("svg")
                    && !extension.equalsIgnoreCase("jpeg")) {
                throw new RuntimeException("Không hỗ trợ định dạng file này");
            }

            try {
                Image img = new Image();
                img.setName(originalFilename);
                img.setType(extension);
                String uid = UUID.randomUUID().toString();
                String link = UPLOAD_DIR + uid + "." + extension;

                // Create file
                File serverFile = new File(link);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(file.getBytes());
                stream.close();

                imageService.save(img);
                return ResponseEntity.ok(img);
            } catch (Exception e) {
                throw new RuntimeException("Lỗi khi upload file");
            }
        }

        throw new RuntimeException("File không hợp lệ");
    }

    // Xóa ảnh theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return ResponseEntity.ok("Xóa thành công ảnh với ID: " + id);
    }
}
