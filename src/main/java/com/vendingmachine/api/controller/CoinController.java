package com.vendingmachine.api.controller;

import com.vendingmachine.api.entity.Coin;
import com.vendingmachine.api.entity.Machine;
import com.vendingmachine.api.exception.VendingMachineException;
import com.vendingmachine.api.exception.VendingMachineExceptionHandler;
import com.vendingmachine.api.service.CoinService;
import com.vendingmachine.api.service.MachineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CoinController {
    
    private CoinService coinService;
    private MachineService machineService;
    
    public CoinController(CoinService coinService, MachineService machineService) {
        this.coinService = coinService;
        this.machineService = machineService;
    }
    
    @GetMapping (path="/coins/insert/{value}", produces = "application/json")
    public ResponseEntity<Object> insertCoin(@PathVariable ("value") @Validated final String value) {
        try {
            coinService.insertCoin(value);
            return ResponseEntity.ok(new Machine(machineService.getCurrentBalance()));
        }
        catch (VendingMachineException e) {
            return VendingMachineExceptionHandler.buildResponseEntity(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }
    
    @GetMapping (path="/coins/refund", produces = "application/json")
    public ResponseEntity<Object> refund() {
        List<Coin> response = coinService.refundCoins();
        return ResponseEntity.ok(response);
    }
}
