package com.example.football.service.impl;

import com.example.football.dto.request.RentRequestDto;
import com.example.football.dto.response.PitchHistoryDto;
import com.example.football.dto.response.RentHistoryDto;
import com.example.football.dto.response.RentMangerDto;
import com.example.football.dto.response.RentStatusResponseDto;
import com.example.football.entity.Buisiness;
import com.example.football.entity.Pitch;
import com.example.football.entity.Rent;
import com.example.football.entity.User;
import com.example.football.repository.BuisinessRepository;
import com.example.football.repository.PitchRepository;
import com.example.football.repository.RentRepository;
import com.example.football.repository.UserRepository;
import com.example.football.service.RentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class RentServiceImpl implements RentService {
    private RentRepository rentRepository;
    private Function<List<Rent>, List<RentStatusResponseDto>> rentStatusResponseDtoFunction;
    private PitchRepository pitchRepository;
    private UserRepository userRepository;
    private Function<RentRequestDto, Rent> rentRequestDtoRentFunction;
    private Function<Page<Rent>, Page<RentHistoryDto>> listRentFunction;
    private BuisinessRepository buisinessRepository;
    private Function<Page<Rent>, Page<RentMangerDto>> pagePageFunction;
    @Override
    public List<RentStatusResponseDto> getRentFindId(long id, Date date) {
        List<Rent> rent = rentRepository.findAllByPitchIdAndDateRentAndStatus(id,date,true);
        if (rent == null){
            return null;
        }
        return rentStatusResponseDtoFunction.apply(rent);
    }

    @Override
    public boolean save(long idPitch, RentRequestDto rentRequestDto, String phone) {
        User user = userRepository.findByPhone(phone);
        Pitch pitch = pitchRepository.findById(idPitch).orElse(new Pitch());
        Rent rent = rentRequestDtoRentFunction.apply(rentRequestDto);
        rent.setPitch(pitch);
        rent.setUser(user);
         rentRepository.save(rent);
        return true;
    }

    @Override
    public Page<RentHistoryDto> history(Pageable pageable, String phone) {
        User user = userRepository.findByPhone(phone);
        Page<Rent> rentList = rentRepository.findAllByUserId(user.getId(),pageable);
        return listRentFunction.apply(rentList);
    }

    @Override
    public Page<RentMangerDto> manageRent(String phoneSearch,Pageable pageable, String phone) {
        User user  = userRepository.findByPhone(phone);
        Buisiness buisiness = buisinessRepository.findByUserId(user.getId());
        List<Pitch> pitches = buisiness.getPitches();
        List<List<Rent>> rentList = new ArrayList<>();
        for (Pitch element: pitches){
            rentList.add(element.getRentList());
        }
        List<Rent> rents = new ArrayList<>();
        if (phoneSearch.equals("")){
            for (List<Rent> elist : rentList){
                for (Rent element : elist){
                    rents.add(element);
                }
            }
        }else {
            Pattern regexPattern = Pattern.compile(phoneSearch);

            for (List<Rent> elist : rentList){
                for (Rent element : elist){
                    Matcher matcher = regexPattern.matcher(element.getUser().getPhone());
                    if (matcher.find()){
                        rents.add(element);
                    }
                }
            }
        }

        Page<Rent> rentPage=convertListToPage(rents,pageable);
        return pagePageFunction.apply(rentPage);
    }
    private Page<Rent> convertListToPage(List<Rent> list, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }


}
