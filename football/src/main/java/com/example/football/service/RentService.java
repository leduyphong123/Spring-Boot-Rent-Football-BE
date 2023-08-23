package com.example.football.service;

import com.example.football.dto.request.RentRequestDto;
import com.example.football.dto.response.RentHistoryDto;
import com.example.football.dto.response.RentMangerDto;
import com.example.football.dto.response.RentStatusResponseDto;
import com.example.football.entity.Rent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public interface RentService {
    List<RentStatusResponseDto> getRentFindId(long id, Date date);

    boolean save(long idPitch, RentRequestDto rentRequestDto, String phone);


    Page<RentHistoryDto> history(Pageable pageable, String phone);

    Page<RentMangerDto> manageRent(String phoneSearch,Pageable pageable, String phone);
}
