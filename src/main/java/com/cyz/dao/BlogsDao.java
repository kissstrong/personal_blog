package com.cyz.dao;

import com.cyz.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogsDao extends JpaRepository<Blog,Long>, JpaSpecificationExecutor<Blog> {
    @Query("select b from Blog b where b.recommend=true")
    List<Blog> findTop(Pageable pageable);
    @Query("select b from Blog b where b.title like ?1 or b.description like ?1")//这里的1是指第一个参数
    Page<Blog> findByQuery(String query,Pageable pageable);

    Page<Blog> findByTypeId(Long typeId,Pageable pageable);

    Page<Blog> findByTagId(Long tagId,Pageable pageable);


    @Query("select b from Blog  b where function('data_format',b.updateTime,'%Y')=?1 ")
    List<Blog> findByYear(String year);

    /*List<Blog> findByUpdateTimeLike(String year);*/
}
