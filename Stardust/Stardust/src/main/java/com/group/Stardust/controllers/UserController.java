package com.group.Stardust.controllers;

import com.group.Stardust.models.Games;
import com.group.Stardust.service.ReviewService;
import com.group.Stardust.service.gameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user/stardust")
public class UserController {

    @Autowired
    private gameService service;

    @Autowired
    private ReviewService reviewService;


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
        model.addAttribute("reviewService", reviewService);


        return "user/store";
    }

}
