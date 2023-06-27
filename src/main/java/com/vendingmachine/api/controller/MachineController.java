package com.vendingmachine.api.controller;

import com.vendingmachine.api.entity.Machine;
import com.vendingmachine.api.response.ResponseHandler;
import com.vendingmachine.api.service.MachineService;
import com.vendingmachine.api.util.JsonUtil;
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
    public ResponseEntity<Object> getCurrentBalance() {
        int currentBalance = machineService.getCurrentBalance();
        return ResponseEntity.ok(JsonUtil.convertToStringWithoutField(new Machine(currentBalance), "id"));
    }
    
    
}
