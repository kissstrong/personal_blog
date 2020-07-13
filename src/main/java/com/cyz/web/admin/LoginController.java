package com.cyz.web.admin;

import com.cyz.po.User;
import com.cyz.service.UserService;
import com.cyz.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @GetMapping("/admin")
    public String loginPage(){
        return "admin/login";
    }

    @PostMapping("/adminlogin")
    public String login(@RequestParam String username, @RequestParam String password
    , HttpSession session, RedirectAttributes attributes){
        User user = userService.checkUser(username, MD5Utils.code(password));
        if (user!=null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else {
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin";
        }
    }

    @RequestMapping("/adminlogout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
