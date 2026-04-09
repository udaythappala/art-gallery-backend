package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.dto.ArtworkDTO;
import com.klu.service.ArtworkService;

@RestController
@RequestMapping("/api/artworks")
@CrossOrigin("*")
public class ArtworkController {

    @Autowired
    private ArtworkService artworkService;

    // ✅ ADD ARTWORK
    @PostMapping
    public ArtworkDTO addArtwork(@RequestBody ArtworkDTO dto) {
        return artworkService.addArtwork(dto);
    }

    // ✅ GET ALL
    @GetMapping
    public List<ArtworkDTO> getAllArtworks() {
        return artworkService.getAllArtworks();
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public ArtworkDTO getArtworkById(@PathVariable Long id) {
        return artworkService.getArtworkById(id);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ArtworkDTO updateArtwork(@PathVariable Long id, @RequestBody ArtworkDTO dto) {
        return artworkService.updateArtwork(id, dto);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public String deleteArtwork(@PathVariable Long id) {
        artworkService.deleteArtwork(id);
        return "Artwork deleted successfully";
    }
}