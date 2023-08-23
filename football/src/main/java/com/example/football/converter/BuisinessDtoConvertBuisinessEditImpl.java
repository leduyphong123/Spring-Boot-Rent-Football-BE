package com.example.football.converter;

import com.example.football.dto.request.BuisinessRequestDto;
import com.example.football.entity.Buisiness;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class BuisinessDtoConvertBuisinessEditImpl implements BuisinessDtoConvertBuisinessEdit{
    @Override
    public Buisiness converter(Buisiness buisiness, BuisinessRequestDto premisesRequestDto) {
        Buisiness premises = buisiness;
        premises.setName(premisesRequestDto.getName());
        premises.setTime_open(stringParseTime(premisesRequestDto.getTime_openStr()));
        premises.setTime_close(stringParseTime(premisesRequestDto.getTime_closeStr()));
        premises.setAddress(premisesRequestDto.getAddress());
        premises.setDescribes(premisesRequestDto.getDescribes());
        premises.setDescription(premisesRequestDto.getDescription());
        return premises;
    }
    private Time stringParseTime(String time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.parse(time,formatter);
        return  Time.valueOf(localTime);
    }
}
