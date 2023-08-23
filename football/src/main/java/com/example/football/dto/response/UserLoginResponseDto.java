package com.example.football.dto.response;

import lombok.Data;

@Data
public class UserLoginResponseDto {
    private String token;
    private boolean status;
}
