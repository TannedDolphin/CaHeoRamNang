package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.CreateDichVuRequest;
import cayxanh.GreencareTest.entity.DichVu;

import java.util.List;

public interface DichVuService {

    List<DichVu> getList();

    DichVu getDichvu(long id);

    DichVu createDichvu(CreateDichVuRequest request);

    DichVu updateDichvu(long id,CreateDichVuRequest request);

    void deleteDichvu(long id);

}
