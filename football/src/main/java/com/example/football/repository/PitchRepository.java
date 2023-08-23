package com.example.football.repository;

import com.example.football.entity.Pitch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PitchRepository extends PagingAndSortingRepository<Pitch,Long> {
    Page<Pitch> findByBuisinessId(long id, Pageable pageable);
}
