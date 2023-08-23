package com.example.football.service;

import com.example.football.dto.request.UserLoginRequestDto;
import com.example.football.dto.request.UserRegisterRequestDto;
import com.example.football.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String getToken(UserLoginRequestDto userLoginRequestDto);

    boolean register(UserRegisterRequestDto userRegisterRequestDto);




    boolean isBuisiness(String phone);

    boolean isUserLogin(String phone);


}