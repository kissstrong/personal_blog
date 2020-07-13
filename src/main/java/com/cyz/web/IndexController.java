package com.cyz.web;
import com.cyz.service.BlogService;
import com.cyz.service.CommentService;
import com.cyz.service.TagsService;
import com.cyz.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TagsService tagsService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private CommentService commentService;
    @GetMapping("/")
    public String index(Model model,@PageableDefault(sort = "updateTime" ,direction = Sort.Direction.ASC,size = 10) Pageable pageable){
      model.addAttribute("page",blogService.listBlog(pageable));
      model.addAttribute("types",typeService.listTypeTop(6));
      model.addAttribute("tags",tagsService.listTagTop(10));
      model.addAttribute("recommendBlogs",blogService.listBlogTop(8));
      return "index";
    }
   @PostMapping("/search")
    public String search(@RequestParam String query, Model model, @PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable) {
        model.addAttribute("page",blogService.listBlog("%"+query+"%",pageable));
        model.addAttribute("query",query);
        return "search";
    }
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model){
      model.addAttribute("blog",blogService.getAndToHtml(id));
        model.addAttribute("comments",commentService.listCommentByBlogId(id));
      return "blog";
    }
    @PostMapping("/typeblog")
    public String queryByType(Long id,Model model,@PageableDefault(sort = "updateTime" ,direction = Sort.Direction.ASC,size = 10) Pageable pageable){
        model.addAttribute("page",blogService.findByTypeId(id,pageable));
        return "index::typeblog";
    }
    @PostMapping("/tagblog")
    public String queryByTag(Long id,Model model,@PageableDefault(sort = "updateTime" ,direction = Sort.Direction.ASC,size = 10) Pageable pageable){
        model.addAttribute("page",blogService.findByTagId(id,pageable));
        return "index::typeblog";
    }
    @PostMapping("/typesblog")
    public String typesblog(Long id,Model model,@PageableDefault(sort = "updateTime" ,direction = Sort.Direction.ASC,size = 10) Pageable pageable){
        model.addAttribute("page",blogService.findByTypeId(id,pageable));
        return "types::typestype";
    }
    @PostMapping("/typeblogtype")
    public String typeblogtype(Long id,Model model,@PageableDefault(sort = "updateTime" ,direction = Sort.Direction.ASC,size = 10) Pageable pageable){
        model.addAttribute("page",blogService.findByTypeId(id,pageable));
        return "types::typestype";
    }
    @PostMapping("/tagsblog")
    public String tagsblog(Long id,Model model,@PageableDefault(sort = "updateTime" ,direction = Sort.Direction.ASC,size = 10) Pageable pageable){
        model.addAttribute("page",blogService.findByTagId(id,pageable));
        return "tags::typestype";
    }
    @PostMapping("/tagblogtag")
    public String tagblogtag(Long id,Model model,@PageableDefault(sort = "updateTime" ,direction = Sort.Direction.ASC,size = 10) Pageable pageable){
        model.addAttribute("page",blogService.findByTypeId(id,pageable));
        return "tags::typestype";
    }
    @GetMapping("/about")
    public String about(){
        return "about";
    }
    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("blogs",blogService.archivesBlog());
        model.addAttribute("count",blogService.countAllBy());
        return "archives";
    }

    @GetMapping("/tags")
    public String tags(@PageableDefault(sort = "updateTime",direction = Sort.Direction.DESC,size = 8)Pageable pageable,Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("tags",tagsService.listTag());
        return "tags";
    }
    @GetMapping("/types")
    public String types(@PageableDefault(sort = "updateTime",direction = Sort.Direction.DESC,size = 8)Pageable pageable,Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listType());
        return "types";
    }
}
