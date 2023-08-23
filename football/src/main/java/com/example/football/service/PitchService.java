package com.example.football.service;

import com.example.football.dto.request.PitchRequestDto;
import com.example.football.dto.response.PageResponseDto;
import com.example.football.dto.response.PitchResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface PitchService {
    boolean registerPitch(PitchRequestDto pitchRequestDto, String phone);


    Page<PitchResponseDto> getList(Pageable pageable, String phone);

    boolean isStatus(Long id, String phone);

    PitchResponseDto getById(Long id, String phone);


    boolean editById(Long id, PitchRequestDto pitchRequestDto, String phone);

    Page<PitchResponseDto> getListFindBuisiness(Pageable pageable, long id);
}
