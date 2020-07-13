package com.cyz.web.admin;

import com.cyz.po.Tag;
import com.cyz.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TagsController {
    @Autowired
    private TagsService tagsService;

    @GetMapping("/admintotags")
    public String toTags(Model model,@PageableDefault(sort = "id" ,size = 10,direction = Sort.Direction.ASC) Pageable pageable){
        model.addAttribute("page",tagsService.listTag(pageable));
        return "admin/tags";
    }

    @GetMapping("/admintoaddtag")
    public String toaddtag(){
        return "admin/tag-add";
    }

    @PostMapping("/adminaddtag")
    public String addtag(Tag tag,Model model){
        Tag tag1 = tagsService.getTagByName(tag.getName());
        if (tag1!=null){
            model.addAttribute("msg","该标签已存在");
            return "admin/tag-add";
        }
        model.addAttribute("message","添加成功");
        tagsService.saveTag(tag);
        return "redirect:/admintotags";
    }

    @GetMapping("/admintoupdatetag/{id}")
    public String toupdatetag(@PathVariable long id,Model model){
        Tag tag = tagsService.getTag(id);
        model.addAttribute("tag",tag);
        return "admin/tag-input";
    }
    @PostMapping("/adminupdatetag")
    public String updatetag(Tag tag){
        tagsService.updateTag(tag);
        return "redirect:/admintotags";
    }
    @GetMapping("/admintodeletetag/{id}")
    public String todeletetag(@PathVariable long id){
        tagsService.deleteTag(id);
        return "redirect:/admintotags";
    }




}
