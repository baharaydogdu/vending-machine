package com.vendingmachine.api.exception;

public class RefundNotAvailableException extends VendingMachineException {
    public RefundNotAvailableException(String message) {
        super(message);
    }
}
