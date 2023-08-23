package com.example.football.converter;

import com.example.football.dto.response.RentHistoryDto;
import com.example.football.entity.Rent;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
@AllArgsConstructor
public class RentConverterRentHistoryDto implements Function<Page<Rent>, Page<RentHistoryDto>> {
    private PitchConverterPitchHistoryDto pitchHistoryDto;


    @Override
    public Page<RentHistoryDto> apply(Page<Rent> rents) {
        return rents.map(rent -> {
            RentHistoryDto rentHistoryDto = new RentHistoryDto();
            BeanUtils.copyProperties(rent,rentHistoryDto);
            rentHistoryDto.setPitch(pitchHistoryDto.apply(rent.getPitch()));
            return rentHistoryDto;
        });
    }
}
