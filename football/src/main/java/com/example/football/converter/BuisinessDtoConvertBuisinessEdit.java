package com.example.football.converter;

import com.example.football.dto.request.BuisinessRequestDto;
import com.example.football.entity.Buisiness;
import org.springframework.stereotype.Component;

@Component
public interface BuisinessDtoConvertBuisinessEdit {
    Buisiness converter (Buisiness buisiness, BuisinessRequestDto premisesRequestDto);
}
