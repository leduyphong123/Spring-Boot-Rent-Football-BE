package com.example.football.converter;

import com.example.football.dto.request.UserRegisterRequestDto;
import com.example.football.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserRegisterRequestDtoConverterUser implements Function<UserRegisterRequestDto, User> {
    @Override
    public User apply(UserRegisterRequestDto userRegisterRequestDto) {
        User user = new User();
        BeanUtils.copyProperties(userRegisterRequestDto,user);
        return user;
    }
}