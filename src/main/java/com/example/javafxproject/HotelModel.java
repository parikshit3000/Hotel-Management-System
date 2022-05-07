package com.example.javafxproject;

public class HotelModel {
    String hid;
    String name;
    int rooms;
    int rating;
    String price;
    String fac;

    public HotelModel() {}

    public HotelModel(String hid, String name, int rooms, int rating, String price, String facility) {
        this.hid=hid;
        this.name = name;
        this.rooms = rooms;
        this.rating = rating;
        this.price = price;
        this.fac = facility;
    }

    public String getHid() {
        return hid;
    }

    public int getRating() {
        return rating;
    }

    public int getRooms() {
        return rooms;
    }

    public String getFac() {
        return fac;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
