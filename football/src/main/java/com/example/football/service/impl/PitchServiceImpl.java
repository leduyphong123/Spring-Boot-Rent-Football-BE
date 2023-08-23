package com.example.football.service.impl;

import com.example.football.converter.PitchEditConverterPitchEdit;
import com.example.football.dto.request.PitchRequestDto;
import com.example.football.dto.response.PitchResponseDto;
import com.example.football.entity.Buisiness;
import com.example.football.entity.Pitch;
import com.example.football.entity.User;
import com.example.football.repository.BuisinessRepository;
import com.example.football.repository.PitchRepository;
import com.example.football.repository.UserRepository;
import com.example.football.service.PitchService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class PitchServiceImpl implements PitchService {
    private PitchRepository pitchRepository;
    private Function<PitchRequestDto, Pitch> requestDtoPitchFunction;
    private UserRepository userRepository;
    private BuisinessRepository buisinessRepository;
    private Function<Page<Pitch>,Page<PitchResponseDto>> pagePageFunction;
    private Function<Pitch, PitchResponseDto> pitchPitchResponseDtoFunction;
    private PitchEditConverterPitchEdit pitchEditConverterPitchEdit;
    @Override
    public boolean registerPitch(PitchRequestDto pitchRequestDto, String phone) {
        User user= userRepository.findByPhone(phone);
        Buisiness buisiness = buisinessRepository.findByUserId(user.getId());
        Pitch pitch = requestDtoPitchFunction.apply(pitchRequestDto);
        pitch.setBuisiness(buisiness);
        pitch.setStatus(true);
        pitchRepository.save(pitch);
        return false;
    }

    @Override
    public Page<PitchResponseDto> getList(Pageable pageable, String phone) {
        User user= userRepository.findByPhone(phone);
        Buisiness buisiness = buisinessRepository.findByUserId(user.getId());
        Page<Pitch> pitches = pitchRepository.findByBuisinessId(buisiness.getId(),pageable);
        return pagePageFunction.apply(pitches);
    }

    @Override
    public boolean isStatus(Long id, String phone) {
        User user= userRepository.findByPhone(phone);
        Buisiness buisiness = buisinessRepository.findByUserId(user.getId());
        Pitch pitch = pitchRepository.findById(id).orElseThrow( () -> { throw new IllegalArgumentException("Id not fourd");});
        if (pitch.getBuisiness().getId() != buisiness.getId()){
            return false;
        }
        pitch.setStatus(!pitch.isStatus());
        pitchRepository.save(pitch);
        return true;
    }

    @Override
    public PitchResponseDto getById(Long id, String phone) {
        Pitch pitch= pitchRepository.findById(id).orElse(new Pitch());
        return pitchPitchResponseDtoFunction.apply(pitch);
    }

    @Override
    public boolean editById(Long id, PitchRequestDto pitchRequestDto, String phone) {
        Pitch pitch= pitchRepository.findById(id).orElse(new Pitch());
        if (pitch==null){
            return false;
        }
        pitch= pitchEditConverterPitchEdit.converter(pitch,pitchRequestDto);
        pitchRepository.save(pitch);

        return true;
    }

    @Override
    public Page<PitchResponseDto> getListFindBuisiness(Pageable pageable, long id) {
        Buisiness buisiness = buisinessRepository.findById(id).orElse(new Buisiness());
        Page<Pitch> pitches = pitchRepository.findByBuisinessId(buisiness.getId(),pageable);
        return pagePageFunction.apply(pitches);
    }


}
