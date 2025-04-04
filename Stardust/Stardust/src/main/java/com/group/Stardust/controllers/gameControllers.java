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
        int size = 2; // or however many you want to display
        List<Games> games = gameService.getGames(search, page, size);
        model.addAttribute("games", games);
        return "home";
    }

    /**
     * Handles requests for the store page with optional search and pagination parameters.
     *
     * @param search Search keyword to filter games by title.
     * @param page   Current page index (0-based).
     * @param size   Number of games per page.
     * @param model  Model object to pass data to the view.
     * @return The Thymeleaf template name to render.
     */

    @GetMapping("/store")
    public String store(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size,
            Model model
    ){
        // Retrieve filtered and paginated list of games.
        List<Games> games = service.getGames(search, page, size);
        // Calculate the total number of pages.
        int totalPages = service.getTotalPages(size);

        // Pass data to the Thymeleaf template.
        model.addAttribute("games", games);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("search", search);

        return "store";
    }



}
