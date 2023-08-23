package com.example.football.converter;

import com.example.football.dto.request.PitchRequestDto;
import com.example.football.entity.Pitch;
import org.springframework.stereotype.Component;

@Component
public class PitchEditConverterPitchEditImpl implements PitchEditConverterPitchEdit{
    @Override
    public Pitch converter(Pitch pitch, PitchRequestDto pitchRequestDto) {
        pitch.setName(pitchRequestDto.getName());
        pitch.setMin_people(pitchRequestDto.getMin_people());
        pitch.setMax_people(pitchRequestDto.getMax_people());
        pitch.setPrice(pitchRequestDto.getPrice());
        return pitch;
    }
}
