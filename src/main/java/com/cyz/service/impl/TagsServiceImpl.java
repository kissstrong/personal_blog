package com.cyz.service.impl;

import com.cyz.dao.TagsDao;
import com.cyz.po.Tag;
import com.cyz.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagsServiceImpl implements TagsService {
    @Autowired
    private TagsDao tagsDao;
    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagsDao.save(tag);
    }
    @Transactional
    @Override
    public Tag getTagByName(String name) {
        return tagsDao.findByName(name);
    }
    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagsDao.getOne(id);
    }
    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagsDao.findAll(pageable);
    }
    @Transactional
    @Override
    public Tag updateTag(Tag Tag) {
        return tagsDao.save(Tag);
    }

    @Override
    public List<Tag> listTag() {
        return tagsDao.findAll();
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort=Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable= PageRequest.of(0,size,sort);
        return tagsDao.findTop(pageable);
    }

    @Override
    public Tag findByid(Long id) {
        return tagsDao.getOne(id);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
         tagsDao.deleteById(id);
    }
}
