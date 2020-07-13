package com.cyz.service;

import com.cyz.po.Blog;
import com.cyz.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {
    Blog saveBlog(Blog blog);

    Blog getBlog(Long id);
    List<Blog> listBlogTop(Integer size);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(String query,Pageable pageable);

    Blog updateBlog(Blog blog);

    void deleteBlog(Long id);

    Page<Blog> findByTypeId(Long typeId,Pageable pageable);

    Page<Blog> findByTagId(Long tagId,Pageable pageable);

    Blog getAndToHtml(Long id);
    Long countAllBy();
    List<Blog> archivesBlog();
}
