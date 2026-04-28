package com.klu.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.klu.dto.ArtworkDTO;
import com.klu.entity.Artwork;
import com.klu.repository.ArtworkRepository;

@Service
public class ArtworkService {

    @Autowired
    private ArtworkRepository artworkRepository;

    @Autowired
    private ModelMapper modelMapper;

    // 🔥 ABSOLUTE PATH
    private final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

    public ArtworkDTO uploadArtwork(String title, String description, double price,
                                    String artist, String category, MultipartFile file) {

        try {
            // 🔥 Create folder safely
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 🔥 Check empty file
            if (file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }

            // 🔥 Clean filename (important)
            String originalName = file.getOriginalFilename();
            String safeName = originalName.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");

            // 🔥 Unique name
            String fileName = UUID.randomUUID() + "_" + safeName;

            // 🔥 Full path
            Path filePath = uploadPath.resolve(fileName);

            // 🔥 DEBUG
            System.out.println("Saving file to: " + filePath.toString());
            System.out.println("File size: " + file.getSize());

            // 🔥 Save file (more reliable than transferTo)
            Files.copy(file.getInputStream(), filePath);

            // 🔥 Save to DB
            Artwork artwork = new Artwork();
            artwork.setTitle(title);
            artwork.setDescription(description);
            artwork.setPrice(price);
            artwork.setArtist(artist);
            artwork.setCategory(category);
            artwork.setImage(fileName);

            Artwork saved = artworkRepository.save(artwork);

            return modelMapper.map(saved, ArtworkDTO.class);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("❌ File upload failed: " + e.getMessage());
        }
    }

    // ✅ ADD
    public ArtworkDTO addArtwork(ArtworkDTO dto) {
        Artwork artwork = modelMapper.map(dto, Artwork.class);
        Artwork saved = artworkRepository.save(artwork);
        return modelMapper.map(saved, ArtworkDTO.class);
    }

    // ✅ GET ALL
    public List<ArtworkDTO> getAllArtworks() {
        return artworkRepository.findAll()
                .stream()
                .map(artwork -> modelMapper.map(artwork, ArtworkDTO.class))
                .collect(Collectors.toList());
    }

    // ✅ GET BY ID
    public ArtworkDTO getArtworkById(Long id) {
        Artwork artwork = artworkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artwork not found"));

        return modelMapper.map(artwork, ArtworkDTO.class);
    }

    // ✅ UPDATE
    public ArtworkDTO updateArtwork(Long id, ArtworkDTO dto) {

        Artwork artwork = artworkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artwork not found"));

        artwork.setTitle(dto.getTitle());
        artwork.setArtist(dto.getArtist());
        artwork.setPrice(dto.getPrice());
        artwork.setImage(dto.getImage());
        artwork.setDescription(dto.getDescription());
        artwork.setCategory(dto.getCategory());

        Artwork updated = artworkRepository.save(artwork);

        return modelMapper.map(updated, ArtworkDTO.class);
    }

    // ✅ DELETE
    public void deleteArtwork(Long id) {

        if (!artworkRepository.existsById(id)) {
            throw new RuntimeException("Artwork not found");
        }

        artworkRepository.deleteById(id);
    }
}