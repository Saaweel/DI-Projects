package com.saaweel;

import java.util.Date;

public class Product {
    private String name;

    private double price;

    private int stock;

    private double weight;

    private Date expire;

    private String color;

    private String material;

    private String model;

    public Product(String name, double price, int stock, double weight, Date expire, String color, String material, String model) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.weight = weight;
        this.expire = expire;
        this.color = color;
        this.material = material;
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
