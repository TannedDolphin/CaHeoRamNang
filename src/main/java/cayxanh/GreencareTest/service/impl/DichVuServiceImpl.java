package cayxanh.GreencareTest.service.impl;

import cayxanh.GreencareTest.dto.request.CreateDichVuRequest;
import cayxanh.GreencareTest.entity.DichVu;
import cayxanh.GreencareTest.entity.Tag;
import cayxanh.GreencareTest.entity.User;
import cayxanh.GreencareTest.exception.NotFoundException;
import cayxanh.GreencareTest.repo.DichVuRepository;
import cayxanh.GreencareTest.repo.ImageRepository;
import cayxanh.GreencareTest.repo.TagRepository;
import cayxanh.GreencareTest.repo.UserRepository;
import cayxanh.GreencareTest.service.DichVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import cayxanh.GreencareTest.entity.Image;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DichVuServiceImpl implements DichVuService {

    @Autowired
    private DichVuRepository dichVuRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<DichVu> getList() {
        // TODO Auto-generated method stub
        return dichVuRepository.findAll(Sort.by("id").descending());
    }

    @Override
    public DichVu getDichvu(long id){
        DichVu service = dichVuRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Service"));
        return service;
    }

    @Override
    public DichVu createDichvu(CreateDichVuRequest request) {
        // TODO Auto-generated method stub
        DichVu service = new DichVu();
        service.setTitle(request.getTitle());
        service.setDescription(request.getDescription());
        service.setContent(request.getContent());
        Image image = imageRepository.findById(request.getImageId()).orElseThrow(() -> new NotFoundException("Not Found Image"));
        service.setImage(image);
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(()-> new NotFoundException("Not Found User"));
        service.setUser(user);
        Set<Tag> tags = new HashSet<>();
        for(Long tagId : request.getTags()){
            Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new NotFoundException("Not Found Tag"));
            tags.add(tag);
        }
        service.setTags(tags);
        dichVuRepository.save(service);
        return service;
    }

    @Override
    public DichVu updateDichvu(long id, CreateDichVuRequest request) {
        // TODO Auto-generated method stub
        DichVu service = dichVuRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Blog"));
        service.setTitle(request.getTitle());
        service.setDescription(request.getDescription());
        service.setContent(request.getContent());
        Image image = imageRepository.findById(request.getImageId()).orElseThrow(() -> new NotFoundException("Not Found Image"));
        service.setImage(image);
        Set<Tag> tags = new HashSet<>();
        for(Long tagId : request.getTags()){
            Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new NotFoundException("Not Found Tag"));
            tags.add(tag);
        }
        service.setTags(tags);
        dichVuRepository.save(service);
        return service;
    }

    @Override
    public void deleteDichvu(long id) {
        // TODO Auto-generated method stub
        DichVu service = dichVuRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Blog"));
        service.getTags().remove(this);
        dichVuRepository.delete(service);
    }

}
