package com.klu.dto;

public class ArtworkDTO {

    private Long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String artist; // email

    // ✅ Constructors
    public ArtworkDTO() {}

    public ArtworkDTO(Long id, String title, String description, double price, String image, String artist) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.image = image;
        this.artist = artist;
    }

    // ✅ Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }
}