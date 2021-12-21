package com.example.newyear.controllers;

import com.example.newyear.Service.WishServiceImpl;
import com.example.newyear.models.Wish;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class WishController {

    private final WishServiceImpl wishService;

//    @GetMapping("/")
//    public String mainPage(Model model){
//        List<Wish> wishes = wishService.getAllWishes();
//        model.addAttribute("wishes", wishes);
//        return "main-wish";
//    }

}
