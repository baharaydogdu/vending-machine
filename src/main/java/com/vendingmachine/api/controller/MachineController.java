package com.vendingmachine.api.controller;

import com.vendingmachine.api.entity.Machine;
import com.vendingmachine.api.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MachineController {
    
    private final MachineService machineService;
    
    @Autowired
    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }
    
    @GetMapping (path="/machine/currentBalance", produces = "application/json")
    public ResponseEntity<Machine> getCurrentBalance() {
        int currentBalance = machineService.getCurrentBalance();
        return ResponseEntity.ok(new Machine(currentBalance));
    }
    
    
}
