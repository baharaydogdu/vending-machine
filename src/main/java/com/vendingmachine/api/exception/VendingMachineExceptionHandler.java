package com.vendingmachine.api.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

public class VendingMachineExceptionHandler {
    
    public static ResponseEntity<Object> buildResponseEntity(HttpStatus status, String message) {
        VendingMachineError error = new VendingMachineError(status, message);
        return new ResponseEntity<>(error, error.status);
    }
    
    public static class VendingMachineError {
    
        private HttpStatus status;
        @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        private LocalDateTime timestamp;
        private String message;
        private String debugMessage;
    
        private VendingMachineError() {
            timestamp = LocalDateTime.now();
        }
    
        VendingMachineError(HttpStatus status) {
            this();
            this.status = status;
        }
    
        public VendingMachineError(HttpStatus status, String message) {
            this();
            this.status = status;
            this.message = message;
        }
    
        VendingMachineError(HttpStatus status, String message, Throwable ex) {
            this();
            this.status = status;
            this.message = message;
            this.debugMessage = ex.getLocalizedMessage();
        }
    }
}
