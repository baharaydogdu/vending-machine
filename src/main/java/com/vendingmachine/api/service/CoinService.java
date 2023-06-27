package com.vendingmachine.api.service;

import com.vendingmachine.api.entity.Coin;
import com.vendingmachine.api.exception.RefundNotAvailableException;
import com.vendingmachine.api.exception.VendingMachineException;

import java.util.List;

public interface CoinService {
    
    void insertCoin(String coin) throws VendingMachineException;
    
    void removeCoin(String coin);
    
    void addCoins(List<Coin> coins);
    
    List<Coin> refundCoins() throws VendingMachineException;
    
    List<Coin> getMachineCoins();
}
