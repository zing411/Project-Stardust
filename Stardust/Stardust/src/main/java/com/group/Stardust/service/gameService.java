package com.group.Stardust.service;

import com.group.Stardust.models.Games;
import com.group.Stardust.repositories.ExternalGameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//contains the logic for fetching and processing game data
@Service
public class gameService {
    private final ExternalGameRepository externalRepo;

    // Constructor-based dependency injection for the external repository.
    public gameService(ExternalGameRepository externalRepo) {
        this.externalRepo = externalRepo;
    }

    /**
     * Retrieves a filtered and paginated list of games.
     * Fetches all games from the external API, applies an optional search filter, and paginates the result.
     *
     * @param search Search keyword to filter games by title.
     * @param page   Current page index (0-based).
     * @param size   Number of games per page.
     * @return A list of games for the requested page.
     */

    public List<Games> getGames(String search, int page, int size) {
        List<Games> allGames = externalRepo.getAllGames();

        // Filter games by title if a search keyword is provided.
        if (search != null && !search.isEmpty()) {
            allGames = allGames.stream()
                    .filter(g -> g.getTitle().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        }

        // Calculate pagination indices.
        int start = page * size;
        int end = Math.min(start + size, allGames.size());

        // Return an empty list if the start index is beyond available data.
        if (start > allGames.size()) return List.of();

        return allGames.subList(start, end);
    }

    /**
     * Calculates the total number of pages based on the number of games and page size.
     *
     * @param size Number of games per page.
     * @return Total number of pages.
     */

    public int getTotalPages(int size) {
        int total = externalRepo.getAllGames().size();
        return (int) Math.ceil((double) total / size);
    }


    }
