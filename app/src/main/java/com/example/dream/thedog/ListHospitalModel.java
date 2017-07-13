package com.example.dream.thedog;

/**
 * Created by dream on 7/13/17.
 */

public class ListHospitalModel {
    private String name, address;
    private Double lat, lng;

    public ListHospitalModel(String name, String address, Double lat, Double lng) {
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
