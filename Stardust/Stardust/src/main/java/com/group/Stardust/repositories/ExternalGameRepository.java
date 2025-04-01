package com.group.Stardust.repositories;

import com.group.Stardust.models.Games;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Repository
public class ExternalGameRepository {
    private static final String API_URL = "https://www.freetogame.com/api/games\n";// replace with our actual api fetches from games api

    private final RestTemplate restTemplate;

    // Constructor instantiates RestTemplate for API calls.
    public ExternalGameRepository() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Fetches all games from the external API.
     * Assumes the external API returns a JSON array mapping to the Games class.
     *
     * @return A list of Games fetched from the external API.
     */
    public List<Games> getAllGames() {
        Games[] gamesArray = restTemplate.getForObject(API_URL, Games[].class);
        return Arrays.asList(gamesArray);
    }

}
