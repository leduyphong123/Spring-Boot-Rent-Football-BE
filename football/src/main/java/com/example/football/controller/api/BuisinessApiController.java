package com.example.football.controller.api;

import com.example.football.dto.request.BuisinessRequestDto;
import com.example.football.dto.response.BuiSinessResponseDto;
import com.example.football.service.BuisinessService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class BuisinessApiController {

    private BuisinessService buisinessService;


    @PostMapping("register-buisiness")
    public ResponseEntity<Boolean> registerBuisiness(@RequestBody BuisinessRequestDto buisinessRequestDto, HttpServletRequest request) {
       String phone =(String) request.getAttribute("phone");
        boolean isStatus = buisinessService.registerBuisinessPremises(buisinessRequestDto, phone);
        return getBooleanResponseEntity(isStatus);
    }

    @PostMapping("edit-buisiness/{id}")
    public ResponseEntity<Boolean> editBuisiness(@PathVariable Long id, @RequestBody BuisinessRequestDto buisinessRequestDto, HttpServletRequest request) {
        String phone =(String) request.getAttribute("phone");
        boolean isStatus = buisinessService.editBuisinessPremises(buisinessRequestDto, phone, id);
        return getBooleanResponseEntity(isStatus);
    }

    @PostMapping("buisiness-status/{id}")
    public ResponseEntity<Boolean> statusBuisiness(@PathVariable Long id, HttpServletRequest request) {
        String phone =(String) request.getAttribute("phone");
        boolean isStatus = buisinessService.statusBuisinessPremises(phone, id);
        return getBooleanResponseEntity(isStatus);
    }

    @GetMapping("buisiness")
    public ResponseEntity<BuiSinessResponseDto> getBuisinessByUser(@RequestParam Long id, HttpServletRequest request) {
        String phone = (String) request.getAttribute("phone");
        BuiSinessResponseDto responseDto = buisinessService.getBuisinessByUser(id, phone);
        if (responseDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @GetMapping("buisiness/user")
    public ResponseEntity<BuiSinessResponseDto> getBuisiness( HttpServletRequest request) {
        String phone = (String) request.getAttribute("phone");
        BuiSinessResponseDto responseDto = buisinessService.getBuisiness(phone);
        if (responseDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("home/like")
    public ResponseEntity<List<BuiSinessResponseDto>> getHomeLike() {
        List<BuiSinessResponseDto> responseDto = buisinessService.getHomeLike();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("home/view")
    public ResponseEntity<List<BuiSinessResponseDto>> getHomeView() {
        List<BuiSinessResponseDto> responseDto = buisinessService.getHomeView();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("home/new")
    public ResponseEntity<List<BuiSinessResponseDto>> getHomeNew() {
        List<BuiSinessResponseDto> responseDto = buisinessService.getHomeNew();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("buisiness/list")
    public ResponseEntity<Page<BuiSinessResponseDto>> getList(@RequestParam String search, @RequestParam String param, @RequestParam String sor, @PageableDefault(value = 9) Pageable pageable) {
        Page<BuiSinessResponseDto> responseDtos = buisinessService.getList(search, param, sor, pageable);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    private static ResponseEntity<Boolean> getBooleanResponseEntity(boolean isRegisterStatus) {
        if (isRegisterStatus) {
            return new ResponseEntity<>(isRegisterStatus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(isRegisterStatus, HttpStatus.BAD_REQUEST);
        }
    }
}