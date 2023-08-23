package com.example.football.converter;

import com.example.football.dto.response.PitchResponseDto;
import com.example.football.entity.Pitch;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PitchconveterPitchResponseDto implements Function<Pitch, PitchResponseDto> {
    @Override
    public PitchResponseDto apply(Pitch pitch) {
        PitchResponseDto pitchResponseDto = new PitchResponseDto();
        BeanUtils.copyProperties(pitch,pitchResponseDto);
        return pitchResponseDto;
    }
}
