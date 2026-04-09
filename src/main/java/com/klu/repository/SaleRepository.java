package com.klu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.klu.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findByBuyerId(Long buyerId);
}