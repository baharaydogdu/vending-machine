package com.vendingmachine.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Machine {
    @Id
    @GeneratedValue
    private Long id;
    
    private int currentBalance;
    
    public Machine(int currentBalance) {
        this.currentBalance = currentBalance;
    }
}
