package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.CreateDichVuRequest;
import cayxanh.GreencareTest.entity.DichVu;
import cayxanh.GreencareTest.entity.Category;
import cayxanh.GreencareTest.repo.DichVuRepo;
import cayxanh.GreencareTest.repo.CategoryRepo; // Giả định bạn có repo cho Category
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DichVuService {

    @Autowired
    private DichVuRepo dichVuRepo;

    @Autowired
    private CategoryRepo categoryRepo; // Giả định bạn có repo cho Category

    public DichVu createDichVu(CreateDichVuRequest request) {
        DichVu dichVu = new DichVu();
        dichVu.setDichvuname(request.getDichvuname());
        dichVu.setDichvudescription(request.getDichvudescription());

        Category category = categoryRepo.findById(request.getCategoryid())
                .orElseThrow(() -> new RuntimeException("Category không tồn tại với ID: " + request.getCategoryid()));
        dichVu.setCategoryservice(category);

        return dichVuRepo.save(dichVu);
    }

    public DichVu updateDichVu(Integer id, CreateDichVuRequest request) {
        DichVu dichVu = dichVuRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("DichVu không tồn tại với ID: " + id));

        dichVu.setDichvuname(request.getDichvuname());
        dichVu.setDichvudescription(request.getDichvudescription());

        Category category = categoryRepo.findById(request.getCategoryid())
                .orElseThrow(() -> new RuntimeException("Category không tồn tại với ID: " + request.getCategoryid()));
        dichVu.setCategoryservice(category);

        return dichVuRepo.save(dichVu);
    }

    public void deleteDichVu(Integer id) {
        if (!dichVuRepo.existsById(id)) {
            throw new RuntimeException("DichVu không tồn tại với ID: " + id);
        }
        dichVuRepo.deleteById(id);
    }

    public List<DichVu> getAllDichVu() {
        return dichVuRepo.findAll();
    }

    public DichVu getDichVuById(Integer id) {
        return dichVuRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("DichVu không tồn tại với ID: " + id));
    }

    public List<DichVu> getDichVuByName(String name) {
        return dichVuRepo.findByName(name).stream().toList();
    }
}
