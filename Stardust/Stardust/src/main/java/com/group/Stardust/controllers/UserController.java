package com.group.Stardust.controllers;

import com.group.Stardust.models.Games;
import com.group.Stardust.models.Review;
import com.group.Stardust.service.ReviewService;
import com.group.Stardust.service.gameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user/stardust")
public class UserController {

    @Autowired
    private gameService service;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/store")
    public String store(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size,
            Model model
    ){
        List<Games> games = service.getGames(search, page, size);
        int totalPages = service.getTotalPages(size);

        model.addAttribute("games", games);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("search", search);
        model.addAttribute("reviewService", reviewService);

        return "user/store";
    }

    // 🔥 Add this to handle submitted reviews
    @PostMapping("/store/review")
    public String submitReview(@RequestParam String gameTitle,
                               @RequestParam String comment,
                               @RequestParam int rating,
                               @RequestParam String username) {
        Review review = new Review();
        review.setGameTitle(gameTitle);
        review.setComment(comment);
        review.setRating(rating);
        review.setUsername(username);

        reviewService.saveReview(review);

        return "redirect:/user/stardust/store";
    }
}
