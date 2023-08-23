package com.example.football.service.impl;

import com.example.football.config.JwtTokenUtil;
import com.example.football.contraint.Role;
import com.example.football.dto.request.UserLoginRequestDto;
import com.example.football.dto.request.UserRegisterRequestDto;
import com.example.football.entity.User;
import com.example.football.repository.UserRepository;
import com.example.football.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenUtil jwtTokenUtil;
    private Function<UserRegisterRequestDto, User> requestDtoUserFunction;
    @Override
    public String getToken(UserLoginRequestDto userLoginRequestDto) {
        User user = userRepository.findByPhone(userLoginRequestDto.getPhone());
        if (passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword())){
            if (user.isStatus()){
                return jwtTokenUtil.generateToken(user);
            }
        }
        return null;
    }

    @Override
    public boolean register(UserRegisterRequestDto userRegisterRequestDto) {
        userRegisterRequestDto.setPassword(passwordEncoder.encode(userRegisterRequestDto.getPassword()));
        User user = requestDtoUserFunction.apply(userRegisterRequestDto);
        user.setRole("ROLE_".concat(String.valueOf(Role.USER)));
        user.setStatus(true);
        userRepository.save(user);
        return true;
    }



    @Override
    public boolean isBuisiness(String phone) {
        User user = userRepository.findByPhone(phone);
        if (user== null){
            return false;
        }
        String[] userRole = user.getRole().split(",");
        for (String element : userRole){
            if (element.equals("ROLE_".concat(String.valueOf(Role.BUISINESS)))){
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isUserLogin(String phone) {
        User user = userRepository.findByPhone(phone);
        if (user == null){
            return false;
        }
        return true;

    }


}