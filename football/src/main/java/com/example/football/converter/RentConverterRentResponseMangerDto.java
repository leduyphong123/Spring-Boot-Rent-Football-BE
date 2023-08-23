package com.example.football.converter;

import com.example.football.dto.response.RentMangerDto;
import com.example.football.entity.Rent;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@AllArgsConstructor
public class RentConverterRentResponseMangerDto implements Function<Page<Rent>, Page<RentMangerDto>> {
    private PitchConverterPitchHistoryDto pitchHistoryDto;


    @Override
    public Page<RentMangerDto> apply(Page<Rent> rents) {
        return rents.map(rent -> {
            RentMangerDto rentHistoryDto = new RentMangerDto();
            BeanUtils.copyProperties(rent,rentHistoryDto);
            rentHistoryDto.setPitch(pitchHistoryDto.apply(rent.getPitch()));
            rentHistoryDto.setPhone(rent.getUser().getPhone());
            return rentHistoryDto;
        });
    }
}
