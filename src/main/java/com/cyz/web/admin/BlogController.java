package com.cyz.web.admin;

import com.cyz.po.Blog;
import com.cyz.po.Comment;
import com.cyz.po.User;
import com.cyz.service.BlogService;
import com.cyz.service.TagsService;
import com.cyz.service.TypeService;
import com.cyz.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagsService tagsService;
    @GetMapping("/adminblogs")
    public String blogs(BlogQuery blog, @PageableDefault(size = 10,sort = "id",direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return "admin/blogs";
    }
    @PostMapping("/adminblogssearch")
    public String blogssearch(BlogQuery blog,@PageableDefault(size = 10,sort = "id",direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return "admin/blogs::bloglist";
    }
    @GetMapping("/toadminblogs-add")
    public String toadminblogsadd(Model model){
        model.addAttribute("tags",tagsService.listTag());
        model.addAttribute("types",typeService.listType());
        return "admin/blogs-add";
    }
    @PostMapping("/adminblogsadd")
    public String adminblogsadd(Blog blog, HttpSession session){
        blog.setCreateTime(new Date());
        blog.setUser((User) session.getAttribute("user"));
        blogService.saveBlog(blog);
        return "redirect:/adminblogs";
    }
    @GetMapping("/toadminblogupdate/{id}")
    public String toadminblogupdate(@PathVariable("id") Long id,Model model,HttpSession session){
        Blog blog = blogService.getBlog(id);
        blog.setTag(tagsService.getTag(blog.getTag().getId()));
        blog.setType(typeService.getType(blog.getType().getId()));
        session.setAttribute("createTime",blog.getCreateTime());
        session.setAttribute("views",blog.getViews());
        session.setAttribute("comments",blog.getComments());
        model.addAttribute("blog",blog);
        model.addAttribute("tags",tagsService.listTag());
        model.addAttribute("types",typeService.listType());
        return "admin/blogs-input";
    }
    @PostMapping("/adminblogsupdate")
    public String adminblogsupdate(Blog blog,HttpSession session){
        System.out.println(blog);
        blog.setCreateTime((Date)session.getAttribute("createTime"));
        blog.setViews((Integer) session.getAttribute("views"));
        blog.setUser((User) session.getAttribute("user"));
        blog.setComments((List<Comment>) session.getAttribute("comments"));
        blog.setUpdateTime(new Date());
        blogService.updateBlog(blog);
        return "redirect:/adminblogs";
    }
    @GetMapping("/adminblogdelete/{id}")
        public String adminblogdelete(@PathVariable("id") Long id){
            blogService.deleteBlog(id);
            return "redirect:/adminblogs";
    }
}
