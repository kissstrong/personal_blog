package com.cyz.service;

import com.cyz.po.Tag;
import com.cyz.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagsService {
    Tag saveTag(Tag tag);
    Tag  getTagByName(String name);
    Tag getTag(Long id);
    Page<Tag> listTag(Pageable pageable);
    Tag updateTag(Tag Tag);
    List<Tag> listTag();
    List<Tag> listTagTop(Integer size);
    Tag findByid(Long id);
    void deleteTag(Long id);
}
