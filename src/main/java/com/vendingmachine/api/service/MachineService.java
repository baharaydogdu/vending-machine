package com.vendingmachine.api.service;

public interface MachineService {
    int getCurrentBalance();
    
    void addBalance(int balance);
    
    void removeBalance(int price);
}
