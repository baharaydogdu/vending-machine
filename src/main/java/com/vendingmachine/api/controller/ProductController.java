package com.vendingmachine.api.controller;

import com.vendingmachine.api.entity.Machine;
import com.vendingmachine.api.entity.Product;
import com.vendingmachine.api.exception.VendingMachineException;
import com.vendingmachine.api.exception.VendingMachineExceptionHandler;
import com.vendingmachine.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    
    private final ProductService productService;
    
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping (path="/products", produces = "application/json")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getAvailableProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    
    }
    
    
    @GetMapping (path="/products/buy/{id}", produces = "application/json")
    public ResponseEntity<Object> buyProductById(@PathVariable ("id") @Validated final String id) {
        try {
            int currentBalance = productService.buyProductById(id);
            return ResponseEntity.ok(new Machine(currentBalance));
        }
        catch (VendingMachineException e) {
            return VendingMachineExceptionHandler.buildResponseEntity(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

}
