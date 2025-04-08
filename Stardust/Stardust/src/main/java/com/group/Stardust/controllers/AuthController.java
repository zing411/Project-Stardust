package com.group.Stardust.controllers;

import com.group.Stardust.models.MyUser;
import com.group.Stardust.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/error")
    public String handlerror() {
        return "auth/error";
    }

    // handles the login
    @GetMapping("/login")
    public String login(Model model, @RequestParam(required = false) String message) {
        model.addAttribute("message", message);
        return "auth/login";
    }

    // handles the logout
    @GetMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse res, Authentication auth) {
        new SecurityContextLogoutHandler().logout(req, res, auth);
        return "auth/login";
    }

    // Handles the new users
    @GetMapping("/register")
    public String register(org.springframework.ui.Model model, @RequestParam(required = false) String message) {
        model.addAttribute("message", message);
        model.addAttribute("user", new MyUser());
        return "auth/register";
    }

    // Messages if username already exists
    @PostMapping("/register")
    public String register(@ModelAttribute MyUser user) {
        // save the user
        int statusCode = userService.saveUser(user);

        if(statusCode == 0){
            return "redirect:/register?message=Username already exists! Please try another username!";
        }

        return "redirect:/login?message=Username has been registered successfully! Hope you enjoy our service!";
    }

}
