package com.example.football.converter;

import com.example.football.dto.response.BuiSinessResponseDto;
import com.example.football.entity.Buisiness;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BuisinessConverterBuiSinessResponseDto implements Function<Buisiness, BuiSinessResponseDto> {
    @Override
    public BuiSinessResponseDto apply(Buisiness buisiness) {
        BuiSinessResponseDto buiSinessResponseDto = new BuiSinessResponseDto();
        BeanUtils.copyProperties(buisiness, buiSinessResponseDto);
        return buiSinessResponseDto;
    }
}
