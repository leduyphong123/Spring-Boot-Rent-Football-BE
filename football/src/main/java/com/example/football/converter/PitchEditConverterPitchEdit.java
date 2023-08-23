package com.example.football.converter;

import com.example.football.dto.request.PitchRequestDto;
import com.example.football.entity.Pitch;
import org.springframework.stereotype.Component;

@Component
public interface PitchEditConverterPitchEdit {
    Pitch converter(Pitch pitch, PitchRequestDto pitchRequestDto);
}
