package com.vendingmachine.api.exception;

public class CoinNotValidException extends VendingMachineException {
    public CoinNotValidException(String message) {
        super(message);
    }
}
