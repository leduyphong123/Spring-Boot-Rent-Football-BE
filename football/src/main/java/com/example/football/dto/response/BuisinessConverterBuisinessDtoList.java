package com.example.football.dto.response;

import com.example.football.entity.Buisiness;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BuisinessConverterBuisinessDtoList implements Function<Page<Buisiness>,Page<BuiSinessResponseDto>> {
    @Override
    public Page<BuiSinessResponseDto> apply(Page<Buisiness> buisinesses) {
        return buisinesses.map( buisiness -> {
            BuiSinessResponseDto responseDto = new BuiSinessResponseDto();
            BeanUtils.copyProperties(buisiness,responseDto);
            return responseDto;
        });
    }
}
