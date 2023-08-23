package com.example.football.controller.api;

import com.example.football.dto.request.RentRequestDto;
import com.example.football.dto.response.RentHistoryDto;
import com.example.football.dto.response.RentMangerDto;
import com.example.football.dto.response.RentStatusResponseDto;
import com.example.football.entity.Rent;
import com.example.football.service.RentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/rent/")
@AllArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class RentController {
    private RentService rentService;

    @GetMapping("{id}/{date}")
    public ResponseEntity<List<RentStatusResponseDto>> getRentFindPitchId(@PathVariable long id, @PathVariable Date date){
        List<RentStatusResponseDto> rentResponseDto = rentService.getRentFindId(id,date);

        return new ResponseEntity<>(rentResponseDto, HttpStatus.OK);
    }
    @PostMapping("create/{idPitch}")
    public ResponseEntity<Boolean> createRentByUser(@PathVariable long idPitch, @RequestBody RentRequestDto rentRequestDto,HttpServletRequest request){
                String phone = String.valueOf(request.getAttribute("phone"));
        boolean isResult = rentService.save(idPitch,rentRequestDto,phone);
        return new ResponseEntity<>(isResult,HttpStatus.OK);
    }
    @GetMapping("history")
    public ResponseEntity<Page<RentHistoryDto>> history(@PageableDefault(value = 5) Pageable pageable,HttpServletRequest request){
                String phone = String.valueOf(request.getAttribute("phone"));
        Page<RentHistoryDto> rentList =rentService.history(pageable,phone);
        return new ResponseEntity<>(rentList,HttpStatus.OK);
    }
    @GetMapping("manage-rent")
    public ResponseEntity<Page<RentMangerDto>> manageRent(@RequestParam String search,@PageableDefault(value = 5) Pageable pageable,HttpServletRequest request){
        String phone = String.valueOf(request.getAttribute("phone"));
        Page<RentMangerDto> rentList =rentService.manageRent(search,pageable,phone);
        return new ResponseEntity<>(rentList,HttpStatus.OK);
    }
}
