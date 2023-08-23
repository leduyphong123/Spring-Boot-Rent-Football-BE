package com.example.football.dto.response;

import lombok.Data;

import java.sql.Time;

@Data
public class BuisinessHistoryDto {
    private String name;
    private Time time_open;
    private Time time_close;
    private String address;
}
