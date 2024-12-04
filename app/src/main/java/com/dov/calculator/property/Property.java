package com.dov.calculator.property;

public class Property {
    String type;
    String surface;
    String constructionYear;
    String price;
    String address;
    String city;
    boolean furnished;
    boolean parking;
    int typeRadio;

    public Property(String type, String surface, String constructionYear, String price, String address, String city, boolean furnished, boolean parking, int typeRadio) {
        this.type = type;
        this.surface = surface;
        this.constructionYear = constructionYear;
        this.price = price;
        this.address = address;
        this.city = city;
        this.furnished = furnished;
        this.parking = parking;
        this.typeRadio = typeRadio;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public String getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(String constructionYear) {
        this.constructionYear = constructionYear;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isFurnished() {
        return furnished;
    }

    public void setFurnished(boolean furnished) {
        this.furnished = furnished;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public int getTypeRadio() {
        return typeRadio;
    }

    public void setTypeRadio(int typeRadio) {
        this.typeRadio = typeRadio;
    }

}