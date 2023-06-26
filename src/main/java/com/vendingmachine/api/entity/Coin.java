package com.vendingmachine.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Coin {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private int quantity;
    
    public Coin(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
        return "Coin{" +
            "name='" + this.name + '\'' +
            ", quantity='" + this.quantity + '\'' +
            "}\n";
    }
}
