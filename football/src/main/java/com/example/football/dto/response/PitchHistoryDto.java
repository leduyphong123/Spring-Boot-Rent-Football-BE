package com.example.football.dto.response;

import lombok.Data;

@Data
public class PitchHistoryDto {
    private String name;
    private int minPeople;
    private int maxPeople;
    private long price;
    private BuisinessHistoryDto buisiness;
}
