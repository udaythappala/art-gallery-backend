package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.entity.Sale;
import com.klu.service.SaleService;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin("*")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping
    public Sale recordSale(@RequestBody Sale sale) {
        return saleService.recordSale(sale);
    }

    @GetMapping
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/buyer/{id}")
    public List<Sale> getSalesByBuyer(@PathVariable Long id) {
        return saleService.getSalesByBuyer(id);
    }
}