package com.example.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("name", "Aryan");
        return "index";
    }

    @RequestMapping("/helloo")
    public String sayHello(){
        return "index.html";
    }
}
