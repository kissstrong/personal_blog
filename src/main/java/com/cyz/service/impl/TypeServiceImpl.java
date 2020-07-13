package com.cyz.service.impl;
import com.cyz.dao.TypeDao;
import com.cyz.po.Type;
import com.cyz.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeDao typeDao;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeDao.save(type);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.findByName(name);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeDao.getOne(id);
    }
    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeDao.findAll(pageable);
    }
    @Transactional
    @Override
    public Type updateType(Type type) {
        return typeDao.save(type);
    }
    @Transactional
    @Override
    public void deleteType(Long id) {
        typeDao.deleteById(id);

    }
    @Transactional
    @Override
    public List<Type> listType() {
        return typeDao.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort=Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable =PageRequest.of(0, size, sort);
        return typeDao.findTop(pageable);
    }

    @Override
    public Type findByid(Long id) {
        return typeDao.getOne(id);
    }
}
