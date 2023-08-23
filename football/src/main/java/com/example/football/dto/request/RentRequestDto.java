package com.example.football.dto.request;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class RentRequestDto {
    private Date dateRent;
    private String timeStartStr;
    private String timeEndStr;
    private Date dateCreate;
    private boolean status;
}
