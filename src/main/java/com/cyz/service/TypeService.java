package com.cyz.service;

import com.cyz.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {
    Type saveType(Type type);
    Type getTypeByName(String name);
    Type getType(Long id);
    Page<Type> listType(Pageable pageable);
    Type updateType(Type type);
    void deleteType(Long id);
    List<Type> listType();
    List<Type> listTypeTop(Integer size);
    Type findByid(Long id);
}
