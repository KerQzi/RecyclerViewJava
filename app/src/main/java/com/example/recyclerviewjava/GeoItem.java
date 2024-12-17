package com.example.recyclerviewjava;

public class GeoItem {
    private String name;
    private String imageUrl;
    private String description;

    public GeoItem(String name, String imageUrl, String description) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }
}
