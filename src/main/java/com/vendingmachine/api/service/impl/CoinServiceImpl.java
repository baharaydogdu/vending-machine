package com.vendingmachine.api.service.impl;

import com.vendingmachine.api.entity.Coin;
import com.vendingmachine.api.entity.model.CoinType;
import com.vendingmachine.api.exception.CoinNotValidException;
import com.vendingmachine.api.exception.RefundNotAvailableException;
import com.vendingmachine.api.exception.VendingMachineException;
import com.vendingmachine.api.repository.CoinRepository;
import com.vendingmachine.api.service.CoinService;
import com.vendingmachine.api.service.MachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CoinServiceImpl implements CoinService {
    private final Logger logger = LoggerFactory.getLogger(CoinServiceImpl.class);
    
    private final CoinRepository coinRepository;
    private final MachineService machineService;
    private final List<Coin> refundCoins = new ArrayList<>();
    
    @Autowired
    public CoinServiceImpl(CoinRepository coinRepository, MachineService machineService) {
        this.coinRepository = coinRepository;
        this.machineService = machineService;
    }
    
    @Override
    public void insertCoin(String coin) throws VendingMachineException {
        String coinValue = validateCoin(coin);
        Coin coinToBeInserted = coinRepository.findByName(coin);
        int currentQuantity = coinToBeInserted.getQuantity();
        coinToBeInserted.setQuantity(currentQuantity+1);
        
        coinRepository.saveAndFlush(coinToBeInserted);
        machineService.addBalance(Integer.parseInt(coinValue));
    }
    
    @Override
    public void removeCoin(String coin) {
        Coin coinToBeInserted = coinRepository.findByName(coin);
        int currentQuantity = coinToBeInserted.getQuantity();
        coinToBeInserted.setQuantity(currentQuantity-1);
        
        coinRepository.saveAndFlush(coinToBeInserted);
    }
    
    @Override
    public void addCoins(List<Coin> coins) {
        coinRepository.saveAll(coins);
    }
    
    @Override
    public List<Coin> refundCoins() throws VendingMachineException {
        AtomicInteger refund = new AtomicInteger(machineService.getCurrentBalance());
        if(refund.get() == 0) {
            throw new RefundNotAvailableException("There is no balance to be refunded.");
        }
        
        refundCoins.clear();
        while (refund.get() != 0) {
            Coin refundableCoin = searchRefundableCoin(refund);
            refundCoins.add(refundableCoin);
            refund.set(refund.get() - Integer.parseInt(refundableCoin.getName()) * refundableCoin.getQuantity());
        }
    
        refundCoins.forEach(refundCoin -> {
            Coin coinToBeUpdated = coinRepository.findByName(refundCoin.getName());
            coinToBeUpdated.setQuantity(coinToBeUpdated.getQuantity() - refundCoin.getQuantity());
            this.coinRepository.saveAndFlush(coinToBeUpdated);
        });
        
        machineService.removeBalance(machineService.getCurrentBalance());
        return refundCoins;
    }
    
    private Coin searchRefundableCoin(AtomicInteger refund) {
        AtomicReference<Coin> coin = new AtomicReference<>();
        AtomicInteger quantity = new AtomicInteger(201);
        Arrays.stream(CoinType.values())
            .forEach(type -> this.coinRepository.findAll().forEach(repoCoin -> {
                if (type.getName().equals(repoCoin.getName()) && repoCoin.getQuantity() > 0 && Integer.parseInt(repoCoin.getName()) <= refund.get()) {
                    double division = Math.floor(refund.get() / Integer.parseInt(repoCoin.getName()));
                    if(division >= 1 && division < quantity.get()) {
                        quantity.set((int) division);
                        coin.set(new Coin(type.getName(), (int) division));
                    }
                }
            }));
        return coin.get();
    }
    
    @Override
    public List<Coin> getMachineCoins() {
        return coinRepository.findAll();
    }
    
    
    private String validateCoin(String coin) throws VendingMachineException {
        AtomicBoolean isValid = new AtomicBoolean(false);
        
        try {
            Arrays.stream(CoinType.values())
                .forEach(type -> {
                    if (type.getName().equals(coin)) {
                        logger.info("Coin <{}> is inserted successfully.", coin);
                        isValid.set(true);
                    }
                });
        } catch (NumberFormatException e) {
            throw new CoinNotValidException("Coin is not accepted. Please enter a valid number.");
        }
        if (! isValid.get()) {
            throw new CoinNotValidException("Coin is not accepted. Please enter a valid coin.");
        }
        return coin;
    }
}
