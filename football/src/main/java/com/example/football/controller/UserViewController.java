package com.example.football.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserViewController {

    @GetMapping("/login")
    public ModelAndView loginView (){
        return new ModelAndView("login");
    }
    //    @GetMapping("/register")
//    public ModelAndView registerView (){
//        return new ModelAndView("register","userRegisterRequestDto",new UserRegisterRequestDto());
//    }
    @GetMapping("/home")
    public ModelAndView home (){
        return new ModelAndView("home");
    }
}