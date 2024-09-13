package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.dto.request.CreateTagRequest;
import cayxanh.GreencareTest.dto.response.MessageResponse;
import cayxanh.GreencareTest.entity.Tag;
import cayxanh.GreencareTest.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
@CrossOrigin(origins = "*",maxAge = 3600)
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public ResponseEntity<List<Tag>> getList(){

        List<Tag> list = tagService.getListTag();

        return ResponseEntity.ok(list);
    }

    @PostMapping("/create")
    public ResponseEntity<Tag> createTag(@RequestBody CreateTagRequest request){
        Tag tag = tagService.createTag(request);
        return ResponseEntity.ok(tag);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable long id,@RequestBody CreateTagRequest request){
        Tag tag = tagService.updateTag(id, request);

        return ResponseEntity.ok(tag);
    }
    @PutMapping("/enable/{id}")
    public ResponseEntity<?> enabled(@PathVariable long id){
        tagService.enableTag(id);
        return ResponseEntity.ok(new MessageResponse("Enable tag success"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable long id){
        tagService.deleleTag(id);

        return ResponseEntity.ok(new MessageResponse("Delete tag success"));

    }

}
