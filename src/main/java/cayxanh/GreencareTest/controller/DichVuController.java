package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.dto.request.CreateDichVuRequest;
import cayxanh.GreencareTest.dto.response.MessageResponse;
import cayxanh.GreencareTest.entity.DichVu;
import cayxanh.GreencareTest.service.DichVuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
@CrossOrigin(origins = "*",maxAge = 3600)
public class DichVuController {

    @Autowired
    private DichVuService dichVuService;

    @GetMapping("/")
    public ResponseEntity<List<DichVu>> getList(){
        List<DichVu> list = dichVuService.getList();

        return ResponseEntity.ok(list);

    }

    @GetMapping("/{id}")
    public ResponseEntity<DichVu> getDichvu(@PathVariable long id){

        DichVu service =dichVuService.getDichvu(id);
        return ResponseEntity.ok(service);

    }

    @PostMapping("/create")
    public ResponseEntity<DichVu> create(@RequestBody CreateDichVuRequest request){

        DichVu service = dichVuService.createDichvu(request);

        return ResponseEntity.ok(service);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DichVu> update(@PathVariable long id, @RequestBody CreateDichVuRequest request){

        DichVu service = dichVuService.updateDichvu(id, request);

        return ResponseEntity.ok(service);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        dichVuService.deleteDichvu(id);
        return ResponseEntity.ok(new MessageResponse("Delete success"));
    }

}
