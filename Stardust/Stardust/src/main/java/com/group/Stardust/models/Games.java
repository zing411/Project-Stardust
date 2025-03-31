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
    private double price;
    private double rating;

}
