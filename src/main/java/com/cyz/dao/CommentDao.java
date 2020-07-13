package com.cyz.dao;

import com.cyz.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment,Long> {
List<Comment> findByBlogId(Long blogId, Sort sort);

}
