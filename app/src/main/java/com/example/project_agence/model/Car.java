package com.example.project_agence.model;

public class Car {
    private String brand;
    private String model;
    private int year;
    private String color;
    private double price;
    private String imageUrl;

    // Constructeur par défaut nécessaire pour Firebase
    public Car() {
    }

    // Constructeur avec tous les paramètres
    public Car(String brand, String model, int year, String color, double price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.price = price;
    }

    public Car(String brand, String model, int year, String color, double price, String imageUrl) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // Getters et Setters
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}