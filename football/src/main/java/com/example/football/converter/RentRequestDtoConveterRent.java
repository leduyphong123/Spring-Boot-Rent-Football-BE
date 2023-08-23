package com.example.football.converter;

import com.example.football.dto.request.RentRequestDto;
import com.example.football.entity.Rent;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

@Component
public class RentRequestDtoConveterRent implements Function<RentRequestDto, Rent> {

    private Time stringParseTime(String time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.parse(time,formatter);
        return  Time.valueOf(localTime);
    }

    @Override
    public Rent apply(RentRequestDto rentRequestDto) {
        Rent rent = new Rent();
        BeanUtils.copyProperties(rentRequestDto,rent);
        rent.setTimeStart(stringParseTime(rentRequestDto.getTimeStartStr()));
        rent.setTimeEnd(stringParseTime(rentRequestDto.getTimeEndStr()));
        return rent;
    }
}
