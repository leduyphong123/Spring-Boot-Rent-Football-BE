package com.example.football.controller.api;

import com.example.football.contraint.Header;
import com.example.football.dto.request.UserLoginRequestDto;
import com.example.football.dto.request.UserRegisterRequestDto;
import com.example.football.dto.response.UserLoginResponseDto;
import com.example.football.entity.User;
import com.example.football.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UserApiController {

    private UserService userService;

    @PostMapping("login")
    public ResponseEntity<UserLoginResponseDto> login (@RequestBody UserLoginRequestDto userLoginRequestDto, HttpServletResponse response){
        String token = userService.getToken(userLoginRequestDto);
        UserLoginResponseDto responseDto = new UserLoginResponseDto();
        if (token!=null){
            responseDto.setToken(token);
            responseDto.setStatus(true);
            response.addHeader(String.valueOf(Header.Authorization),token);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }else {
            responseDto.setToken("");
            responseDto.setStatus(false);
            return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
        }
    }
    @PostMapping("register")
    public ResponseEntity<Boolean> register (@RequestBody UserRegisterRequestDto userRegisterRequestDto){
        boolean isRegisterStatus = userService.register(userRegisterRequestDto);
        return getBooleanResponseEntity(isRegisterStatus);
    }

    @GetMapping("home")
    public ResponseEntity<Boolean> home (HttpServletRequest request){
        boolean isUserLogin = userService.isUserLogin((String) request.getAttribute("phone"));
        return getBooleanResponseEntity(isUserLogin);
    }



    @PostMapping("is-buisiness")
    public ResponseEntity<Boolean> isBuisiness (HttpServletRequest request){
        boolean isBuisinessStatus = userService.isBuisiness((String) request.getAttribute("phone"));
        return new ResponseEntity<>(isBuisinessStatus, HttpStatus.OK);
    }

    private static ResponseEntity<Boolean> getBooleanResponseEntity(boolean isRegisterStatus) {
        if (isRegisterStatus){
            return new ResponseEntity<>(isRegisterStatus, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(isRegisterStatus, HttpStatus.BAD_REQUEST);
        }
    }
}
