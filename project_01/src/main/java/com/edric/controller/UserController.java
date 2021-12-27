package com.edric.controller;

import com.edric.pojo.User;
import com.edric.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @RequestMapping("/register")
    public String register(String username, String password, String email, String code, Model model, HttpServletRequest request, HttpSession httpSession) {

        code =request.getParameter("code");
        if (!(code.equalsIgnoreCase(httpSession.getAttribute("code").toString()))) {  //忽略验证码大小写
            model.addAttribute("msg", "验证码错误");
            model.addAttribute("username", username);
            model.addAttribute("email", email);
            return "user/regist";
        }

        if (userService.existsUsername(username)) {
            model.addAttribute("msg", "用户名已存在");
            model.addAttribute("username", username);
            model.addAttribute("email", email);
            return "user/regist";
        } else {
            userService.registerUser(new User(null, username, password, email));
            return "user/regist_success";
        }

    }

    @RequestMapping("/login")
    public String login(String username, String password, Model model, HttpSession httpSession) {
        User loginUser = new User(null, username, password, null);
        if (userService.login(loginUser) != null) {
            httpSession.setAttribute("user", loginUser);
            return "user/login_success";
        } else {
            model.addAttribute("msg", "用户名或者密码错误");
            return "user/login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/client/page?pageNo=1";
    }
}
