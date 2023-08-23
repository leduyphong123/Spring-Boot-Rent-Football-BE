package com.example.football.converter;

import com.example.football.dto.response.BuiSinessResponseDto;
import com.example.football.entity.Buisiness;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class BuisinessConverterResponseDtoList implements Function<List<Buisiness>,List<BuiSinessResponseDto>> {
    @Override
    public List<BuiSinessResponseDto> apply(List<Buisiness> buisinessPremises) {
        List<BuiSinessResponseDto> buiSinessResponseDtos = new ArrayList<>();
        for (Buisiness element: buisinessPremises){
            BuiSinessResponseDto responseDto = new BuiSinessResponseDto();
            BeanUtils.copyProperties(element,responseDto);
            buiSinessResponseDtos.add(responseDto);
        }
        return buiSinessResponseDtos;
    }
}
