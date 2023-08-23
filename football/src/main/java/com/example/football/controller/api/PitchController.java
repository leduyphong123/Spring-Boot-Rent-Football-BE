package com.example.football.controller.api;

import com.example.football.dto.request.PitchRequestDto;
import com.example.football.dto.response.PageResponseDto;
import com.example.football.dto.response.PitchResponseDto;
import com.example.football.service.PitchService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/pitch/")
@AllArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class PitchController {
    private PitchService pitchService;

        @PostMapping("register")
    public ResponseEntity<Boolean> registerPitch(@RequestBody PitchRequestDto pitchRequestDto, HttpServletRequest request){
                String phone =(String) request.getAttribute("phone");
        boolean isStatus = pitchService.registerPitch(pitchRequestDto,phone);
        return getBooleanResponseEntity(isStatus);
    }
    @GetMapping("list")
    public ResponseEntity<Page<PitchResponseDto>> getList(@PageableDefault(value = 5) Pageable pageable, HttpServletRequest request){
                String phone =(String) request.getAttribute("phone");
        Page<PitchResponseDto> responseDto = pitchService.getList(pageable,phone);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    @GetMapping("list/find-buisiness")
    public ResponseEntity<Page<PitchResponseDto>> getListFindBuisiness(@RequestParam long id,@PageableDefault(value = 3) Pageable pageable){
        Page<PitchResponseDto> responseDto = pitchService.getListFindBuisiness(pageable,id);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    @GetMapping("edit/{id}")
    public ResponseEntity<PitchResponseDto> getById(@PathVariable Long id, HttpServletRequest request){
                String phone =(String) request.getAttribute("phone");
        PitchResponseDto responseDto = pitchService.getById(id,phone);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    @PostMapping ("edit/{id}")
    public ResponseEntity<Boolean> editById(@PathVariable Long id,@RequestBody PitchRequestDto pitchRequestDto,HttpServletRequest request){
                String phone =(String) request.getAttribute("phone");
        boolean isStatus = pitchService.editById(id,pitchRequestDto,phone);
        return getBooleanResponseEntity(isStatus);
    }

    @PostMapping("status/{id}")
    public ResponseEntity<Boolean> isStatus(@PathVariable Long id,HttpServletRequest request){
                String phone =(String) request.getAttribute("phone");
        boolean isResult = pitchService.isStatus(id,phone);
        return getBooleanResponseEntity(isResult);
    }
    private static ResponseEntity<Boolean> getBooleanResponseEntity(boolean isRegisterStatus) {
        if (isRegisterStatus){
            return new ResponseEntity<>(isRegisterStatus, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(isRegisterStatus, HttpStatus.BAD_REQUEST);
        }
    }
}

