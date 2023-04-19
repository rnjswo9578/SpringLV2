package com.example.bloglv2.user.controller;

import com.example.bloglv2.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @GetMapping("/signup")
//    public ModelAndView signupPage() {
//        return new ModelAndView("static 페이지");
//    }
//
//    @GetMapping("/login")
//    public ModelAndView loginPage() {
//        return new ModelAndView("static 페이지");
//    }

    @PostMapping("/signup")
    public String signup(){
        return null;
    }

    @PostMapping("/login")
    public String login(){
        return null;
    }
}
