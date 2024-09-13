package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.CreateTagRequest;
import cayxanh.GreencareTest.entity.Tag;

import java.util.List;

public interface TagService {

    List<Tag> getListTag();

    Tag createTag(CreateTagRequest request);

    Tag updateTag(long id,CreateTagRequest request);

    void enableTag(long id);

    void deleleTag(long id);

}
