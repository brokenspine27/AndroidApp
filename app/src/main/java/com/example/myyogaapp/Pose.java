package com.example.myyogaapp;

import com.google.firebase.firestore.Exclude;

public class Pose {
    private String id;
    private String title;
    private String description;
    private String imageUrl;

    // Constructor vacío requerido por Firestore
    public Pose() {}

    public Pose(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // Usamos @Exclude para que Firestore no intente mapear este campo al guardar.
    // El ID lo manejaremos manualmente.
    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
