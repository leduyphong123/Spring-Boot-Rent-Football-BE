package com.example.football.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserLoginRequestDto {

    @NotNull
    private String phone;
    @NotNull
    private String password;
}