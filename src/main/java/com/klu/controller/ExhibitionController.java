package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.entity.Exhibition;
import com.klu.service.ExhibitionService;

@RestController
@RequestMapping("/api/exhibitions")
@CrossOrigin("*")
public class ExhibitionController {

    @Autowired
    private ExhibitionService exhibitionService;

    // ✅ CREATE
    @PostMapping
    public Exhibition createExhibition(@RequestBody Exhibition exhibition) {
        return exhibitionService.createExhibition(exhibition);
    }

    // ✅ GET ALL
    @GetMapping
    public List<Exhibition> getAllExhibitions() {
        return exhibitionService.getAllExhibitions();
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public Exhibition updateExhibition(@PathVariable Long id, @RequestBody Exhibition ex) {
        return exhibitionService.updateExhibition(id, ex);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public String deleteExhibition(@PathVariable Long id) {
        exhibitionService.deleteExhibition(id);
        return "Exhibition deleted successfully";
    }
}