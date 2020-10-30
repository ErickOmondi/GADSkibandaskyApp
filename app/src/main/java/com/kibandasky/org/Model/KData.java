package com.kibandasky.org.Model;

public class KData {

    private String image;
    private String title;
    private String description;
    private String Id;

    public KData(){}

    public KData(String image, String title, String description, String id) {
        this.image = image;
        this.title = title;
        this.description = description;
        Id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
