package com.example.football.service.impl;

import com.example.football.contraint.Role;
import com.example.football.converter.BuisinessDtoConvertBuisinessEdit;
import com.example.football.dto.request.BuisinessRequestDto;
import com.example.football.dto.response.BuiSinessResponseDto;
import com.example.football.entity.Buisiness;
import com.example.football.entity.User;
import com.example.football.repository.BuisinessRepository;
import com.example.football.repository.UserRepository;
import com.example.football.service.BuisinessService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class BuisinessServiceImpl implements BuisinessService {

    private UserRepository userRepository;
    private BuisinessRepository buisinessRepository;
    private Function<BuisinessRequestDto, Buisiness> premisesFunction;
    private Function<Buisiness, BuiSinessResponseDto> buiSinessPremisesResponseDtoFunction;
    private BuisinessDtoConvertBuisinessEdit buisinessDtoConvertBuisinessEdit;
    private Function<List<Buisiness>,List<BuiSinessResponseDto>> listBuisinessHomeFunction;
    private Function<Page<Buisiness>,Page<BuiSinessResponseDto>> pageFunction;
    //    private PasswordEncoder passwordEncoder;
    @Override
    public boolean registerBuisinessPremises(BuisinessRequestDto buisinessRequestDto, String phone) {
        User user = userRepository.findByPhone(phone);
        user.setRole(user.getRole().concat(",").concat("ROLE_").concat(String.valueOf(Role.BUISINESS)));
        Buisiness buisiness = premisesFunction.apply(buisinessRequestDto);
        buisiness.setUser(user);
        buisiness.setDateCreate(getDateNow());
        buisiness.setStatus(true);
        buisinessRepository.save(buisiness);
        userRepository.save(user);
        return true;
    }

    @Override
    public BuiSinessResponseDto getBuisinessByUser(long id,String phone) {
        Buisiness buisiness = buisinessRepository.findById(id).orElse(new Buisiness());
        buisiness.setViews(buisiness.getViews()+1);
        BuiSinessResponseDto responseDto =buiSinessPremisesResponseDtoFunction.apply(buisiness);
        responseDto.setTotalPitch(buisiness.getPitches().size());
        buisinessRepository.save(buisiness);
        return responseDto;
    }

    @Override
    public boolean editBuisinessPremises(BuisinessRequestDto buisinessRequestDto, String phone, Long id) {
        User user = userRepository.findByPhone(phone);
        Buisiness premises = buisinessRepository.findById(id).orElse(new Buisiness());
        Buisiness buisiness = buisinessDtoConvertBuisinessEdit.converter(premises, buisinessRequestDto);
        buisinessRepository.save(buisiness);
        return  true;
    }

    @Override
    public boolean statusBuisinessPremises( String phone, Long id) {
        Buisiness buisiness = buisinessRepository.findById(id).orElse(new Buisiness());
        buisiness.setStatus(!buisiness.isStatus());
          buisinessRepository.save(buisiness);
          return true;
    }

    @Override
    public List<BuiSinessResponseDto> getHomeLike() {
        Sort sort = Sort.by(Sort.Direction.DESC, "likes");
        List<Buisiness> buisinessPremises = (List<Buisiness>) buisinessRepository.findAllByStatus(true,sort);
        if(buisinessPremises.size()>=4) {
            List<Buisiness> buisinessResponse = buisinessPremises.subList(0, 4);
            return listBuisinessHomeFunction.apply(buisinessResponse);
        }
        return listBuisinessHomeFunction.apply(buisinessPremises);
    }

    @Override
    public List<BuiSinessResponseDto> getHomeView() {
        Sort sort = Sort.by(Sort.Direction.DESC, "views");
        List<Buisiness> buisinessPremises = (List<Buisiness>) buisinessRepository.findAllByStatus(true,sort);
        if(buisinessPremises.size()>=4) {
            List<Buisiness> buisinessResponse = buisinessPremises.subList(0, 4);
            return listBuisinessHomeFunction.apply(buisinessResponse);
        }
        return listBuisinessHomeFunction.apply(buisinessPremises);
    }

    @Override
    public List<BuiSinessResponseDto> getHomeNew() {
        Sort sort = Sort.by(Sort.Direction.DESC, "dateCreate");
        List<Buisiness> buisinessPremises = buisinessRepository.findAllByStatus(true,sort);
        if(buisinessPremises.size()>=3) {
            List<Buisiness> buisinessResponse = buisinessPremises.subList(0, 3);
            return listBuisinessHomeFunction.apply(buisinessResponse);
        }
        return listBuisinessHomeFunction.apply(buisinessPremises);
    }

    @Override
    public Page<BuiSinessResponseDto> getList(String address,String param, String sor, Pageable pageable) {
        String para="id";
        if (!(param.isEmpty())){
            para=param;
        }
        Sort sort = Sort.by(Sort.Direction.DESC, para);
       if(sor.equals("asc")){
             sort = Sort.by(Sort.Direction.ASC, para);
        }
        Pageable pageableNew = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),sort);
        Page<Buisiness> buisinessList;
       if (address.isEmpty()){
            buisinessList =buisinessRepository.findAllByStatus(true,pageableNew);
       }else {
           String adressLike = "%".concat(address).concat("%");
           buisinessList=buisinessRepository.findAllByAddressLikeAndStatus(adressLike,true,pageableNew);
       }

       return pageFunction.apply(buisinessList);
    }

    @Override
    public BuiSinessResponseDto getBuisiness(String phone) {
        User user = userRepository.findByPhone(phone);
        Buisiness buisiness = buisinessRepository.findByUserId(user.getId());
        BuiSinessResponseDto responseDto =buiSinessPremisesResponseDtoFunction.apply(buisiness);
        responseDto.setTotalPitch(buisiness.getPitches().size());
        return responseDto;
    }


    private Date getDateNow(){
        LocalDate localDate = LocalDate.now();
        return Date.valueOf(localDate);
    }
}