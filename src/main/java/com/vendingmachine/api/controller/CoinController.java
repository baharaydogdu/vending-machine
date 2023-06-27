package com.vendingmachine.api.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.vendingmachine.api.entity.Coin;
import com.vendingmachine.api.entity.Machine;
import com.vendingmachine.api.exception.VendingMachineException;
import com.vendingmachine.api.response.ResponseHandler;
import com.vendingmachine.api.response.ResponseMessage;
import com.vendingmachine.api.service.CoinService;
import com.vendingmachine.api.service.MachineService;
import com.vendingmachine.api.util.JsonUtil;
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
            JsonNode responseData = JsonUtil.convertObjectWithoutField(new Machine(machineService.getCurrentBalance()), "id");
            return ResponseHandler.generateSuccessResponse(ResponseMessage.COIN_INSERTED_SUCCESSFULLY.getMessage(), HttpStatus.OK, responseData);
        } catch (VendingMachineException e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
    
    @GetMapping (path="/coins/refund", produces = "application/json")
    public ResponseEntity<Object> refund() {
        try {
            List<Coin> responseData = coinService.refundCoins();
            return ResponseHandler.generateSuccessResponse(ResponseMessage.COIN_REFUNDED_SUCCESSFULLY.getMessage(),
                HttpStatus.OK, responseData);
        } catch(VendingMachineException e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
}
