package com.vendingmachine.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Product {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String name;
    private int price;
    private int quantity;
    
    public Product(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    public Product(Long id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
        return "Product{" +
            "id='" + this.id + '\'' +
            ", name='" + this.name + '\'' +
            ", price='" + this.price + '\'' +
            ", quantity='" + this.quantity + '\'' +
            "}\n";
    }
}
