package com.example.quanla.quannet.database.models;

/**
 * Created by QuanLA on 3/17/2017.
 */

public class Location {
    private String title;
    private double latitude;
    private double longitude;

    public Location(String title, double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Location{" +
                "latitude=" + latitude +
                ", title='" + title + '\'' +
                ", longitude=" + longitude +
                '}';
    }
}
