package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.dto.request.CreateDichVuRequest;
import cayxanh.GreencareTest.entity.DichVu;
import cayxanh.GreencareTest.service.DichVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dichvus")
public class DichVuController {

    @Autowired
    private DichVuService dichVuService;

    @PostMapping("/create")
    public ResponseEntity<DichVu> createDichVu(@RequestBody CreateDichVuRequest request) {
        DichVu dichVu = dichVuService.createDichVu(request);
        return ResponseEntity.ok(dichVu);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DichVu> updateDichVu(@PathVariable Integer id, @RequestBody CreateDichVuRequest request) {
        DichVu dichVu = dichVuService.updateDichVu(id, request);
        return ResponseEntity.ok(dichVu);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDichVu(@PathVariable Integer id) {
        dichVuService.deleteDichVu(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<DichVu>> getAllDichVu() {
        List<DichVu> dichVus = dichVuService.getAllDichVu();
        return ResponseEntity.ok(dichVus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DichVu> getDichVuById(@PathVariable Integer id) {
        DichVu dichVu = dichVuService.getDichVuById(id);
        return ResponseEntity.ok(dichVu);
    }
}
