package com.cyz.web;

import com.cyz.po.Comment;
import com.cyz.service.BlogService;
import com.cyz.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;
    @GetMapping("/comments/{blogId}")
    public String comment(@PathVariable Long blogId, Model model){
        model.addAttribute("comments",commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }
    @PostMapping("/comments")
    public String post(Comment comment){
        Long id = comment.getBlog().getId();
        System.out.println(id);
        comment.setBlog(blogService.getBlog(id));
        commentService.saveComment(comment);
        return "redirect:/comments/"+id;
    }

}
