package com.group.Stardust.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "auth/error";
    }
}
