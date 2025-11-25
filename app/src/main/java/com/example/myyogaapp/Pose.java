package com.example.myyogaapp;

public class Pose {
    private String title;
    private String description;
    private String imageName; // Cambiado de int a String

    // Constructor vacío requerido por Firestore
    public Pose() {}

    public Pose(String title, String description, String imageName) {
        this.title = title;
        this.description = description;
        this.imageName = imageName;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
