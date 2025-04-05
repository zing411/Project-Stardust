package com.group.Stardust.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Games {
    private String title;
    private String genre;
    private double rating;
    private String short_description;
    private String thumbnail;
    private String developer;
    private String release_date;
    private String publisher;
}
