package com.example.football.converter;

import com.example.football.dto.response.RentStatusResponseDto;
import com.example.football.entity.Rent;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class RentConverterRentStatusResponseDto implements Function<List<Rent>, List<RentStatusResponseDto>> {

    @Override
    public List<RentStatusResponseDto> apply(List<Rent> rents) {
        List<RentStatusResponseDto> dtoList = new ArrayList<>();
        for (Rent element:rents){
            RentStatusResponseDto responseDto = new RentStatusResponseDto();
            BeanUtils.copyProperties(element,responseDto);
            dtoList.add(responseDto);
        }
        return dtoList;
    }
}
