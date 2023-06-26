package com.vendingmachine.api.exception;

public class NotEnoughCreditException extends VendingMachineException {
    public NotEnoughCreditException(String message) {
        super(message);
    }
}
