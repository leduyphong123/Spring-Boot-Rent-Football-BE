package com.example.football.repository;

import com.example.football.entity.Buisiness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuisinessRepository extends PagingAndSortingRepository<Buisiness,Long> {
    Buisiness findByUserId(long user_id);
    List<Buisiness> findAllByStatus(boolean status,Sort sort);
    Page<Buisiness> findAllByStatus(boolean status,Pageable pageable);
    Page<Buisiness> findAllByAddressLikeAndStatus(String address,boolean status,Pageable pageable);

}
