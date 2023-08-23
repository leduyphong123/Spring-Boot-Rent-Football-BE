package com.example.football.dto.request;

import lombok.Data;

@Data
public class PitchRequestDto {
    private String name;
    private int min_people;
    private int max_people;
    private long price;
}
