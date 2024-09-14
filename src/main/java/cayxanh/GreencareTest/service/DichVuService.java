package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.CreateDichVuRequest;
import cayxanh.GreencareTest.entity.DichVu;
import cayxanh.GreencareTest.repo.DichVuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DichVuService {

    @Autowired
    private DichVuRepo dichVuRepo;

    // Tạo DichVu mới
    public DichVu createDichVu(CreateDichVuRequest request) {
        // Tạo dịch vụ mới từ request
        DichVu dichVu = new DichVu();
        dichVu.setDichvuname(request.getDichvuname());
        dichVu.setDichvudescription(request.getDichvudescription());

        // Lưu DichVu vào cơ sở dữ liệu
        return dichVuRepo.save(dichVu);
    }

    // Cập nhật DichVu theo ID
    public DichVu updateDichVu(Integer id, CreateDichVuRequest request) {
        // Tìm DichVu theo ID
        DichVu dichVu = dichVuRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("DichVu không tồn tại với ID: " + id));

        // Cập nhật thông tin của dịch vụ
        dichVu.setDichvuname(request.getDichvuname());
        dichVu.setDichvudescription(request.getDichvudescription());

        // Lưu lại thay đổi
        return dichVuRepo.save(dichVu);
    }

    // Xóa DichVu theo ID
    public void deleteDichVu(Integer id) {
        if (!dichVuRepo.existsById(id)) {
            throw new RuntimeException("DichVu không tồn tại với ID: " + id);
        }
        dichVuRepo.deleteById(id);
    }

    // Lấy tất cả DichVu
    public List<DichVu> getAllDichVu() {
        return dichVuRepo.findAll();
    }

    // Lấy DichVu theo ID
    public DichVu getDichVuById(Integer id) {
        return dichVuRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("DichVu không tồn tại với ID: " + id));
    }
}
