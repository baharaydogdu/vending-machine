package com.vendingmachine.api.entity.model;

public enum ProductType {
    
    CANDY("Candy", 20),
    COOKIE("Cookie", 25),
    CHOCOLATE("Chocolatte", 50),
    CHIPS("Chips", 20),
    WATER("Water", 10),
    COKE("Coke", 50);
    
    private String name;
    private int price;
    
    
    ProductType(String name, int price){
        this.name = name;
        this.price = price;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getPrice() {
        return price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
}
