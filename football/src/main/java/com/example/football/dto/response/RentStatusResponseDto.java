package com.example.football.dto.response;

import lombok.Data;

import java.sql.Time;

@Data
public class RentStatusResponseDto {
    private Time timeStart;
    private Time timeEnd;
    private boolean status;
}
