package com.example.football.repository;

import com.example.football.entity.Rent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface RentRepository extends PagingAndSortingRepository<Rent,Long> {
    List<Rent> findAllByPitchIdAndDateRentAndStatus(long id, Date date,boolean status);
    Page<Rent> findAllByUserId(long id, Pageable pageable);
}
