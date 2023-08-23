package com.example.football.converter;

import com.example.football.dto.request.PitchRequestDto;
import com.example.football.entity.Pitch;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PagePitchRequestDtoConverterPitch implements Function<PitchRequestDto, Pitch> {

    @Override
    public Pitch apply(PitchRequestDto pitchRequestDto) {
        Pitch pitch= new Pitch();
        BeanUtils.copyProperties(pitchRequestDto,pitch);
        return pitch;
    }
}
