package com.example.football.dto.response;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class RentMangerDto {
    private Date dateRent;
    private Time timeStart;
    private Time timeEnd;
    private Date dateCreate;
    private PitchHistoryDto pitch;
    private String phone;
}
