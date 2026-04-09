package com.klu.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.dto.ArtworkDTO;
import com.klu.entity.Artwork;
import com.klu.repository.ArtworkRepository;

@Service
public class ArtworkService {

    @Autowired
    private ArtworkRepository artworkRepository;

    @Autowired
    private ModelMapper modelMapper;

    // ✅ ADD (DTO → Entity)
    public ArtworkDTO addArtwork(ArtworkDTO dto) {
        Artwork artwork = modelMapper.map(dto, Artwork.class);
        Artwork saved = artworkRepository.save(artwork);
        return modelMapper.map(saved, ArtworkDTO.class);
    }

    // ✅ GET ALL (Entity → DTO)
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