package com.example.football.converter;

import com.example.football.dto.response.PitchResponseDto;
import com.example.football.entity.Pitch;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PitchConverterPitchResponseDto implements Function<Page<Pitch>,Page<PitchResponseDto>> {
    @Override
    public Page<PitchResponseDto> apply(Page<Pitch> pitches) {
        return pitches.map( pitch ->{
            PitchResponseDto responseDto = new PitchResponseDto();
            BeanUtils.copyProperties(pitch,responseDto);
            return responseDto;
        });
    }
}
