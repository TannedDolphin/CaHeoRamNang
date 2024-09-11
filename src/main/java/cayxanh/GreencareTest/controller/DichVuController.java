package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.entity.DichVu;
import cayxanh.GreencareTest.service.DichVuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dichvu")
@RequiredArgsConstructor
public class DichVuController {

    private final DichVuService dichVuService;

    // Lấy danh sách tất cả dịch vụ
    @GetMapping
    public ResponseEntity<List<DichVu>> getAllDichVu() {
        List<DichVu> dichVuList = dichVuService.getAllDichVu();
        return new ResponseEntity<>(dichVuList, HttpStatus.OK);
    }

    // Lấy dịch vụ theo ID
    @GetMapping("/{id}")
    public ResponseEntity<DichVu> getDichVuById(@PathVariable int id) {
        DichVu dichVu = dichVuService.getDichVuById(id);
        return new ResponseEntity<>(dichVu, HttpStatus.OK);
    }

    // Tìm dịch vụ theo tên
    @GetMapping("/search")
    public ResponseEntity<DichVu> findByName(@RequestParam String name) {
        DichVu dichVu = dichVuService.findByName(name);
        return dichVu != null ?
                new ResponseEntity<>(dichVu, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Tạo mới một dịch vụ
    @PostMapping
    public ResponseEntity<DichVu> createDichVu(@RequestBody DichVu dichVu) {
        DichVu newDichVu = dichVuService.createdichvu(dichVu);
        return new ResponseEntity<>(newDichVu, HttpStatus.CREATED);
    }

    // Cập nhật thông tin dịch vụ
    @PutMapping("/{id}")
    public ResponseEntity<DichVu> updateDichVu(@PathVariable int id, @RequestBody DichVu dichVu) {
        dichVu.setDichvuid(id); // Đảm bảo ID từ URL được set vào đối tượng dịch vụ
        DichVu updatedDichVu = dichVuService.updateDichvu(id, dichVu);
        return new ResponseEntity<>(updatedDichVu, HttpStatus.OK);
    }

    // Xóa một dịch vụ
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDichVu(@PathVariable int id) {
        dichVuService.deleteDichvu(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
