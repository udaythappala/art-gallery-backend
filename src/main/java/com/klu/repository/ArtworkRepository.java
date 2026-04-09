package com.klu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.klu.entity.Artwork;

public interface ArtworkRepository extends JpaRepository<Artwork, Long> {

}