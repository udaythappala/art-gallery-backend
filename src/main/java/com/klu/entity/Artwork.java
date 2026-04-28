package com.klu.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "artworks")
public class Artwork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Artist is required")
    private String artist; // stored as user.email

    @Positive(message = "Price must be positive")
    private double price;

    @NotBlank(message = "Image is required")
    private String image;

    @Column(length = 1000)
    private String description;

    // 🔥 NEW FIELD
    @NotBlank(message = "Category is required")
    private String category;

    // ✅ GETTERS & SETTERS

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // 🔥 CATEGORY GETTER/SETTER
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}