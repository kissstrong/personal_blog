package com.cyz.service.impl;

import com.cyz.dao.CommentDao;
import com.cyz.po.Comment;
import com.cyz.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort=Sort.by(Sort.Direction.DESC,"createTime");
        List<Comment> comments = commentDao.findByBlogId(blogId, sort);
        return comments;
    }

    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId=comment.getParentComment().getId();
        if (parentCommentId!=-1){
            comment.setParentComment(commentDao.getOne(parentCommentId));
        }else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentDao.save(comment);
    }

}
