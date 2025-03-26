package com.group.Stardust.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stardust")
public class gameControllers {

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/store")
    public String store(){
        return "store";
    }

}
