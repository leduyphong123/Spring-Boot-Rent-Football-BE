package com.example.football.dto.response;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class BuiSinessResponseDto {
    private long id;
    private String name;
    private Time time_open;
    private Time time_close;
    private Date dateCreate;
    private String address;
    private String describes;
    private String description;
    private long likes;
    private long views;
    private String img;
    private int totalPitch;
    private boolean status;
}
