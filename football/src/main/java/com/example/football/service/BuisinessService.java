package com.example.football.service;

import com.example.football.dto.request.BuisinessRequestDto;
import com.example.football.dto.response.BuiSinessResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BuisinessService {
    boolean registerBuisinessPremises(BuisinessRequestDto buisinessRequestDto, String phone);

    BuiSinessResponseDto getBuisinessByUser(long id,String phone);

    boolean editBuisinessPremises(BuisinessRequestDto buisinessRequestDto, String phone, Long id);

    boolean statusBuisinessPremises(String phone, Long id);

    List<BuiSinessResponseDto> getHomeLike();

    List<BuiSinessResponseDto> getHomeView();

    List<BuiSinessResponseDto> getHomeNew();

    Page<BuiSinessResponseDto> getList(String address,String param, String sor, Pageable pageable);

    BuiSinessResponseDto getBuisiness(String phone);
}