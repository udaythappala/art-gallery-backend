package com.klu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.entity.Exhibition;
import com.klu.repository.ExhibitionRepository;

@Service
public class ExhibitionService {

    @Autowired
    private ExhibitionRepository exhibitionRepository;

    // ✅ CREATE
    public Exhibition createExhibition(Exhibition exhibition) {
        return exhibitionRepository.save(exhibition);
    }

    // ✅ GET ALL
    public List<Exhibition> getAllExhibitions() {
        return exhibitionRepository.findAll();
    }

    // ✅ UPDATE (SAFE)
    public Exhibition updateExhibition(Long id, Exhibition updated) {

        Exhibition ex = exhibitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exhibition not found"));

        ex.setTitle(updated.getTitle());
        ex.setDescription(updated.getDescription());
        ex.setDate(updated.getDate());
        ex.setCurator(updated.getCurator());
        ex.setCategory(updated.getCategory()); // ✅ IMPORTANT (you added field)

        return exhibitionRepository.save(ex);
    }

    // ✅ DELETE (SAFE)
    public void deleteExhibition(Long id) {

        if (!exhibitionRepository.existsById(id)) {
            throw new RuntimeException("Exhibition not found");
        }

        exhibitionRepository.deleteById(id);
    }
}