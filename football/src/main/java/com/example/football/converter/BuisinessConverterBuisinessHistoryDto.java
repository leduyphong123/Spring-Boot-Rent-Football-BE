package com.example.football.converter;

import com.example.football.dto.response.BuisinessHistoryDto;
import com.example.football.entity.Buisiness;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class BuisinessConverterBuisinessHistoryDto implements Function<Buisiness,BuisinessHistoryDto> {
    @Override
    public BuisinessHistoryDto apply(Buisiness buisinesses) {
        BuisinessHistoryDto buisinessHistoryDto = new BuisinessHistoryDto();
        BeanUtils.copyProperties(buisinesses,buisinessHistoryDto);
        return buisinessHistoryDto;
    }
}
