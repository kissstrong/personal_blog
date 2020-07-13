package com.cyz.web.admin;
import com.cyz.po.Type;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/admintypes")
    public String list(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable
    , Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }
    @GetMapping("/admintypesinput")
    public String input(){
        return "admin/type-add";
    }

    @GetMapping("/admintypesupdate/{id}")
    public String editInput( @PathVariable Long id, Model model){
        model.addAttribute("type", typeService.getType(id));
        return "admin/type-input";
    }
    @GetMapping("/admintypesdelete/{id}")
    public String admintypesdelete( @PathVariable Long id){
        typeService.deleteType(id);
        return "redirect:/admintypes";
    }

    @PostMapping("/admintypes_add")
    public String post(Type type, RedirectAttributes attributes,Model model){
            Type type2 = typeService.getTypeByName(type.getName());
            if (type2!=null){
                model.addAttribute("msg","该类名已存在");
                return "admin/type-add";
            }
            Type type1 = typeService.saveType(type);
            if (type1==null){
                attributes.addFlashAttribute("message","操作失败");
            }else {
                attributes.addFlashAttribute("message","操作成功");
            }
        return "redirect:/admintypes";
    }
    @GetMapping("/admintypes_toupdate/{id}")
    public String toupdate(@PathVariable long id,Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/type-input";
    }
    @PostMapping("/admintypes_update")
    public String admintypes_update(Type type){
            typeService.updateType(type);
        return "redirect:/admintypes";
    }

}
