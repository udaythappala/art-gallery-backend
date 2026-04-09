package com.klu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.klu.entity.Exhibition;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {

}