package com.klu.dto;

import java.time.LocalDate;

public class ExhibitionDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDate date;
    private String curator;
    private String category;

    // ✅ Constructors
    public ExhibitionDTO() {}

    public ExhibitionDTO(Long id, String title, String description,
                         LocalDate date, String curator, String category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.curator = curator;
        this.category = category;
    }

    // ✅ Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getCurator() { return curator; }
    public void setCurator(String curator) { this.curator = curator; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}