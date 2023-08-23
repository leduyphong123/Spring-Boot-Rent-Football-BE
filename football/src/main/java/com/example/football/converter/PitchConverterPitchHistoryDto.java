package com.example.football.converter;

import com.example.football.dto.response.PitchHistoryDto;
import com.example.football.entity.Pitch;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
@AllArgsConstructor
public class PitchConverterPitchHistoryDto implements Function<Pitch, PitchHistoryDto> {
    private BuisinessConverterBuisinessHistoryDto buisinessHistoryDto;
    @Override
    public PitchHistoryDto apply(Pitch pitches) {
        PitchHistoryDto pitchHistoryDto = new PitchHistoryDto();
        BeanUtils.copyProperties(pitches,pitchHistoryDto);
        pitchHistoryDto.setBuisiness(buisinessHistoryDto.apply(pitches.getBuisiness()));
        return pitchHistoryDto;
    }
}
