package com.example.sbmrg.yulu;

public class Location {

    String image;
    String latitude;
    String longitude;
    String title;

    public Location(String image, String latitude, String longitude, String title) {
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getTitle() {
        return title;
    }
}
