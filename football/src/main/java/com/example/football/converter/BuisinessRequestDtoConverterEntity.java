package com.example.football.converter;

import com.example.football.dto.request.BuisinessRequestDto;
import com.example.football.entity.Buisiness;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

@Component
public class BuisinessRequestDtoConverterEntity implements Function<BuisinessRequestDto, Buisiness> {
    @Override
    public Buisiness apply(BuisinessRequestDto buisinessRequestDto) {
        Time time_open= stringParseTime(buisinessRequestDto.getTime_openStr());
        Time time_close= stringParseTime(buisinessRequestDto.getTime_closeStr());
        buisinessRequestDto.setTime_open(time_open);
        buisinessRequestDto.setTime_close(time_close);
        Buisiness buisiness = new Buisiness();
        BeanUtils.copyProperties(buisinessRequestDto, buisiness);
        return buisiness;
    }
    private Time stringParseTime(String time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime = LocalTime.parse(time,formatter);
        return  Time.valueOf(localTime);
    }

}