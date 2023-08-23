package com.example.football.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserRegisterRequestDto {
    @NotNull
    private String phone;
    @NotNull
    private String password;
    private String name;
    private String email;
    private String address;
}