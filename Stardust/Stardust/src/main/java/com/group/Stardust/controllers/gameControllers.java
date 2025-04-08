package com.group.Stardust.controllers;


import com.group.Stardust.models.Games;
import com.group.Stardust.service.gameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/stardust")
public class gameControllers {


    //injecting the gameService to access business logic
    @Autowired
    private gameService service;


    private final gameService gameService; // Consider renaming to GameService per Java conventions


    public gameControllers(gameService gameService) {
        this.gameService = gameService;
    }


    @GetMapping("/home")
    public String home(@RequestParam(required = false) String search,
                       @RequestParam(defaultValue = "0") int page,
                       Model model) {
        int size = 2;
        List<Games> games = gameService.getGames(search, page, size);
        model.addAttribute("games", games);
        return "home";
    }



}
