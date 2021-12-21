package com.example.newyear.controllers;

import com.example.newyear.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/login")
    public String signInPage(){
        return "sign-in";
    }

    @GetMapping("/settings")
    public String settingsPage(Model model){
        return "settings";
    }

    @GetMapping("/logout")
    public String exit(){
        return "sign-in";
    }

}
