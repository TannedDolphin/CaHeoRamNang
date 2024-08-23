package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.entity.DichVu;
import cayxanh.GreencareTest.repo.DichVuRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DichVuService {
    DichVuRepo dichVuRepo;
    public DichVu getDichVuById(int id) {
        return dichVuRepo.findById(id).orElseThrow(()->new RuntimeException("DichVu id " + id + " not found"));
    }
    public List<DichVu> getAllDichVu() {
        List<DichVu> dichVuList = dichVuRepo.findAll();
        if (dichVuList.isEmpty()) {
            throw new RuntimeException("DichVu list is empty");
        }
        return dichVuList;
    }
    public DichVu findByName(String name) {
        Optional<DichVu> dichVu = dichVuRepo.findByName(name);
        return dichVu.orElse(null); // Trả về null nếu không tìm thấy
    }
    public DichVu createdichvu(DichVu dichVu) {
        DichVu existdichVu = dichVuRepo.findByName(dichVu.getDichvuname())
                .orElseThrow(() -> new RuntimeException("service not found"));
        if (existdichVu != null) {
            throw new RuntimeException("service already exists");
        }
        return dichVuRepo.save(dichVu);
    }
    public DichVu updateDichvu(int id) {
        DichVu dichVu1= dichVuRepo.findById(id).orElseThrow(()->new RuntimeException("dichvu not found"));
        updateDichvu(id).getDichvuname();
        updateDichvu(id).getDichvuname();
        updateDichvu(id).getDichvuprice();
        updateDichvu(id).getDichvudescription();
        return dichVuRepo.save(dichVu1);
    }
    public boolean deleteDichvu(int id) {
        DichVu dichVu2= dichVuRepo.findById(id).orElseThrow(()->new RuntimeException("dichvu not found"));
        dichVuRepo.deleteById(dichVu2.getDichvuid());
        return true;
    }
}
