package com.stepien.loginthymeleafsb.controller;

import com.stepien.loginthymeleafsb.model.UserDtls;
import com.stepien.loginthymeleafsb.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/signin")
    public String login() {
        return "login";
    }

    @GetMapping("/register-success")
    public String registerSuccess() {
        return "register-success";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute UserDtls userDtls, HttpSession session) {
        if (userService.checkEmail(userDtls.getEmail())) {
        session.setAttribute("msg", "Email is already taken");
        return "register";
        } else {
        userService.createUser(userDtls);
        }
        return "redirect:/register-success";
    }
}
