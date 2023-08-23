package com.example.football.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Time;

@Data
public class BuisinessRequestDto {
    @NotNull
    private String name;

    @NotNull
    private String time_openStr;

    @NotNull
    private String time_closeStr;
    private Time time_open;

    private Time time_close;

    @NotNull
    private String describes;

    @NotNull
    private String description;

    @NotNull
    private String address;

    @NotNull
    private String img;
}
