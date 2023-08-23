package com.example.football.dto.response;

import lombok.Data;

@Data
public class PitchResponseDto {
    private long id;
    private String name;
    private int min_people;
    private int max_people;
    private long price;
    private boolean status;
}
