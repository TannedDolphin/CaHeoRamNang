package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.entity.Image;
import cayxanh.GreencareTest.exception.BadRequestException;
import cayxanh.GreencareTest.exception.InternalServerException;
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
@RequestMapping("/image")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ImageController {
    private static String UPLOAD_DIR  = System.getProperty("user.dir") + "/src/main/resources/static/images/";

    @Autowired
    private ImageService imageService;


    @GetMapping("/")
    public ResponseEntity<?> getList(){
        List<Image> listImage = imageService.getListImage();

        return  ResponseEntity.ok(listImage);
    }

    @PostMapping("/upload-file")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file){
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);;
        if (originalFilename != null && originalFilename.length() > 0) {
            if (!extension.equals("png") && !extension.equals("jpg") && !extension.equals("gif") && !extension.equals("svg") && !extension.equals("jpeg")) {
                throw new BadRequestException("Không hỗ trợ định dạng file này");
            }
            try {
                Image img = new Image();
                img.setName(file.getName());
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
                throw new InternalServerException("Lỗi khi upload file");
            }
        }

        throw new BadRequestException("File không hợp lệ");

    }
}
