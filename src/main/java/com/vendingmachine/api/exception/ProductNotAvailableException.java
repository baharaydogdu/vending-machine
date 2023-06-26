package com.vendingmachine.api.exception;

public class ProductNotAvailableException extends VendingMachineException {
    public ProductNotAvailableException(String message) {
        super(message);
    }
}
