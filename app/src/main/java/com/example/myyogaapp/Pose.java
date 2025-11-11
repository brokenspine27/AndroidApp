package com.example.myyogaapp;

import android.net.Uri;

public class Pose {
    private String title;
    private String description;
    private int imageResourceId;
    private Uri imageUri;

    // Constructor for drawable resources
    public Pose(String title, String description, int imageResourceId) {
        this.title = title;
        this.description = description;
        this.imageResourceId = imageResourceId;
        this.imageUri = null;
    }

    // Constructor for Uri (gallery images)
    public Pose(String title, String description, Uri imageUri) {
        this.title = title;
        this.description = description;
        this.imageResourceId = 0;
        this.imageUri = imageUri;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public boolean isFromResource() {
        return imageUri == null;
    }
}
