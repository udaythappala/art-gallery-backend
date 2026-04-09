package com.klu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.entity.Sale;
import com.klu.repository.SaleRepository;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    public Sale recordSale(Sale sale) {
        return saleRepository.save(sale);
    }

    public List<Sale> getSalesByBuyer(Long buyerId) {
        return saleRepository.findByBuyerId(buyerId);
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }
}