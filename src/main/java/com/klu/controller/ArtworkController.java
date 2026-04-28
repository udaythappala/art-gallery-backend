package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import com.klu.dto.ArtworkDTO;
import com.klu.dto.ArtworkUploadRequest;
import com.klu.service.ArtworkService;

@RestController
@RequestMapping("/api/artworks")
@CrossOrigin("*")
public class ArtworkController {

    @Autowired
    private ArtworkService artworkService;

    // 🔥 FINAL UPLOAD API (WITH DEBUG + ERROR HANDLING)
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadArtwork(
            @ModelAttribute ArtworkUploadRequest request,
            @RequestParam("file") MultipartFile file) {

        try {
            System.out.println("🔥 CONTROLLER HIT");
            System.out.println("Title: " + request.getTitle());
            System.out.println("Category: " + request.getCategory());
            System.out.println("Artist: " + request.getArtist());
            System.out.println("File: " + file.getOriginalFilename());

            ArtworkDTO result = artworkService.uploadArtwork(
                    request.getTitle(),
                    request.getDescription(),
                    request.getPrice(),
                    request.getArtist(),
                    request.getCategory(),
                    file
            );

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("❌ Upload Failed: " + e.getMessage());
        }
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