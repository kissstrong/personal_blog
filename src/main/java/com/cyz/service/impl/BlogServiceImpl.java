package com.cyz.service.impl;

import com.cyz.dao.BlogsDao;
import com.cyz.po.Blog;
import com.cyz.po.Type;
import com.cyz.service.BlogService;
import com.cyz.util.MarkdownUtils;
import com.cyz.vo.BlogQuery;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {
   @Autowired
    private BlogsDao blogsDao;
   @Transactional
   @Override
    public Blog saveBlog(Blog blog) {
         blog.setViews(0);
        return blogsDao.save(blog);
    }
    @Transactional
    @Override
    public Blog getBlog(Long id) {
        return blogsDao.getOne(id);
    }
    @Transactional
    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {

       return blogsDao.findAll(new Specification<Blog>() {
           @Override
           public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
              List<Predicate> predicates=new ArrayList<>();
              if(!"".equals(blog.getTitle())&&blog.getTitle()!=null){
                  predicates.add(cb.like(root.<String>get("title"),"%"+blog.getTitle()+"%"));
              }
              if (blog.getTypeId()!=null){
                  predicates.add(cb.equal(root.<Type>get("type").get("id"),blog.getTypeId()));
              }
                 if (blog.isRecommend()){
                     predicates.add(cb.equal(root.<Boolean>get("recommend"),blog.isRecommend()));
                 }
              cq.where(predicates.toArray(new Predicate[predicates.size()]));
               return null;
           }
       }, pageable);
    }

    @Override
    public List<Blog> listBlogTop(Integer size) {
        Sort sort=Sort.by(Sort.Direction.DESC,"updateTime");
        Pageable pageable= PageRequest.of(0,size,sort);
       return blogsDao.findTop(pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogsDao.findAll(pageable);
    }

    @Override
    public List<Blog> archivesBlog() {
        List<Blog> all = blogsDao.findAll();
       return all;
    }

    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {

       return blogsDao.findByQuery(query,pageable);
    }

    @Transactional
    @Override
    public Blog updateBlog(Blog blog) {
        return blogsDao.save(blog);
    }
    @Transactional
    @Override
    public void deleteBlog(Long id) {
          blogsDao.deleteById(id);
    }

    @Override
    public Page<Blog> findByTypeId(Long typeId, Pageable pageable) {
        return blogsDao.findByTypeId(typeId,pageable);
    }

    @Override
    public Page<Blog> findByTagId(Long tagId, Pageable pageable) {
        return blogsDao.findByTagId(tagId,pageable);
    }

    @Override
    public Blog getAndToHtml(Long id) {
        Blog one = blogsDao.getOne(id);
        String s = MarkdownUtils.markdownToHtmlExtensions(one.getContent());
        one.setViews(one.getViews()+1);
        blogsDao.save(one);
        one.setContent(s);
        return one;
    }

    @Override
    public Long countAllBy() {
        return blogsDao.count();
    }
}
